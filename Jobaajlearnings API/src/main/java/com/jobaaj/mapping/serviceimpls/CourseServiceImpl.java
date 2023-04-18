package com.jobaaj.mapping.serviceimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.dtos.MarkCourseDto;
import com.jobaaj.mapping.dtos.RatingDto;
import com.jobaaj.mapping.entity.Category;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Lesson;
import com.jobaaj.mapping.entity.Rating;
import com.jobaaj.mapping.entity.Section;
import com.jobaaj.mapping.entity.Trainer;
import com.jobaaj.mapping.entity.WatchHistory;
import com.jobaaj.mapping.exceptions.ResourceNotFound;
import com.jobaaj.mapping.repos.CategoryRepo;
import com.jobaaj.mapping.repos.CourseRepo;
import com.jobaaj.mapping.repos.EnrolRepo;
import com.jobaaj.mapping.repos.LessonRepo;
import com.jobaaj.mapping.repos.SectionRepo;
import com.jobaaj.mapping.repos.StudentRepo;
import com.jobaaj.mapping.repos.TrainerRepo;
import com.jobaaj.mapping.repos.WatchHistoryRepo;
import com.jobaaj.mapping.services.CourseService;


@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepo courseRepo;
	
	
	@Autowired
	private TrainerRepo trainerRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private LessonRepo lessonRepo;
	
	@Autowired
	private SectionRepo sectionRepo;
	
	@Autowired
	private WatchHistoryRepo watchHistoryRepo;
	
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private EnrolRepo enrolRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CourseDto createCourse(CourseDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDto getCourse(Integer courseId) {
		
		
		Course course = this.courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFound("Course","Id",String.valueOf(courseId)));

		CourseDto courseDto = this.courseToDto(course);
		
		//long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
        long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
        
		courseDto.setReview(0);
        courseDto.setEnrolled(enrolCount);
		
		
		return courseDto;
		
		
	}

	@Override
	public List<CourseDto> listofRecommendedCourses(Integer offset, Integer limit,String sortBy) {
		
		
		Pageable page  = PageRequest.of(offset,limit,Sort.by(sortBy));
		
		Page<Course> listPages = this.courseRepo.findAllByStatus(page,1);
		
		List<Course> courses = listPages.getContent();
		
		List<CourseDto> courseDtos = courses.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		        	//long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		        })
		        
		        .collect(Collectors.toList());
		
		
		return courseDtos;
		
		 
	}
	
	public Course dtoToCourse(CourseDto courseDto) {
		return this.modelMapper.map(courseDto,Course.class);
	}
	
	public CourseDto courseToDto(Course course) {
		return this.modelMapper.map(course,CourseDto.class);
	}
	
	
	
	public RatingDto ratingToDto(Rating rating) {
		return this.modelMapper.map(rating,RatingDto.class);
	}


	@Override
	public List<CourseDto> listofPopularCourses(Integer offset, Integer limit) {
		
		
		Pageable page  = PageRequest.of(offset,limit);
		
		Page<Course> listPages = this.courseRepo.findAllByStatus(page,0);
		
		List<Course> courses = listPages.getContent();
		
		List<CourseDto> courseDtos = courses.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		            
		        })
		        .collect(Collectors.toList());
		
		
		return courseDtos;
		
		
	}

	@Override
	public List<CourseDto> searchCourse(String keyword) {

		
		List<Course> fountByTitleLike = this.courseRepo.findByTitleContaining(keyword);
		

		List<CourseDto> courseDtos = fountByTitleLike.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		        	//long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		            
		        })
		        
		        .collect(Collectors.toList());
		
		return courseDtos;
		
		
	}

	@Override
	public List<CourseDto> listofTrainerCourses(Integer trainerId) {
		
		
		Trainer trainer = this.trainerRepo.findById(trainerId).orElseThrow(() -> new ResourceNotFound("Trainer","trainer id",String.valueOf(trainerId)));
		
		
		List<Course> fountByTrainerLike = this.courseRepo.findByTrainerAndStatus(trainer, 0);
		

		List<CourseDto> courseDtos = fountByTrainerLike.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		        //	long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		            
		        })
		        
		        .collect(Collectors.toList());
		
		return courseDtos;
		
		
	}

	@Override
	public List<CourseDto> listofCategoryCourses(Integer catId) {
		

		Category category = this.catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("Category","category id",String.valueOf(catId)));
		
		
		List<Course> fountByCategory = this.courseRepo.findByCategory(category);
		

		List<CourseDto> courseDtos = fountByCategory.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		        	//long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		        })
		        
		        .collect(Collectors.toList());
		
		return courseDtos;
		
	}

	@Override
	public List<CourseDto> listofStudentCourses(Integer userId) {
		
		//Checking Student exist or not
		this.studentRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("Student","student id",String.valueOf(userId)));
		
		
		List<Course> studentCourses = this.courseRepo.selectEnrolledStudents(userId);
		

		List<CourseDto> courseDtos = studentCourses.stream()
		        .map(course -> {
		           
		        	CourseDto courseDto = this.courseToDto(course);
		           
		        //	long ratingCount = ratingRepo.countByrateableId(courseDto.getId());
		            long enrolCount = enrolRepo.countByCourseId(courseDto.getCid());
		            
		            courseDto.setReview(0);
		            courseDto.setEnrolled(enrolCount);
		            return courseDto;
		        })
		        .collect(Collectors.toList());
		
		return courseDtos;
		
	}

	
	@Override
	public String markLesson(MarkCourseDto mCDto) {
		
		
		long timestamp = System.currentTimeMillis();
		
		
	     WatchHistory watchHistory =  watchHistoryRepo.findByCourseAndStudent(mCDto.getCourseId(),mCDto.getStudentId());		
		
	     if(watchHistory==null) {
	    	 
	    	
	    	 WatchHistory watchHistory1 = new WatchHistory();
	    	 
	    	 watchHistory1.setCourse(mCDto.getCourseId());
	    	 watchHistory1.setStudent(mCDto.getStudentId());    
	    	 watchHistory1.setDate_added(timestamp);	    	 
	    	 watchHistory1.setQuiz_result(null);    	 
	    	 watchHistory1.setWatching_lesson_id(mCDto.getLessonId()); 	 
	    	
	    	 List<Integer> lessons = Arrays.asList(mCDto.getLessonId());
	    	 
	    	 watchHistory1.setCompleted_lesson(lessons);  	
	    	 
	    	 watchHistoryRepo.save(watchHistory1);	   

	    	 
	    	 return "Row Inserted";
	    	 
	    	 
	     } else {	
	    	 

	    	 List<Integer> lessons = watchHistory.getCompleted_lesson();
	    	 
	    	 	    	 
	    	 
	    	 WatchHistory watchHistory1 = new WatchHistory();
	    	 
	    	 watchHistory1.setWatching_lesson_id(mCDto.getLessonId()); 	
	    	 
	    	 watchHistory1.setWhid(watchHistory.getWhid());	 
	    	 
	    	 watchHistory1.setCourse(watchHistory.getCourse());
	    	 watchHistory1.setStudent(watchHistory.getStudent());    
	    	 watchHistory1.setDate_added(timestamp);	    	 
	    	 watchHistory1.setQuiz_result(null);    	 
	    	 
	    	 
	    	 if(lessons.contains(mCDto.getLessonId())){
	    		 
		    	 lessons.remove(Integer.valueOf(mCDto.getLessonId()));
	    	 }else {
	    		 lessons.add(mCDto.getLessonId());
	    	 }
	    	 
	    	 
	    	 watchHistory1.setCompleted_lesson(lessons);  	
	    	 
	    	 watchHistoryRepo.save(watchHistory1);	   
	    	 
	    	 
	    	 return "Lesson Updated!";
	     }
	 
	     
		
		
		
	}

    public String markSection(MarkCourseDto mCDto) {
		
		
		long timestamp = System.currentTimeMillis();
		
		
	     WatchHistory watchHistory =  watchHistoryRepo.findByCourseAndStudent(mCDto.getCourseId(),mCDto.getStudentId());		
		
	     if(watchHistory==null) {
	    	
	    	 
	    	 Section sec  = sectionRepo.findById(mCDto.getSectionId()).orElseThrow(() -> new ResourceNotFound("Course","Id",String.valueOf(mCDto.getSectionId())));
		    	
	    	 List<Lesson> secLessons = lessonRepo.findBySection(sec);    
	    	 
			 List<Integer> lessons = new ArrayList<>();

	    			
	    	 secLessons.stream().forEach(secLes -> lessons.add(secLes.getLid()));    	 

	    	 
	    	 WatchHistory watchHistory1 = new WatchHistory();
	    	 
	    	 watchHistory1.setCourse(mCDto.getCourseId());
	    	 watchHistory1.setStudent(mCDto.getStudentId());    
	    	 watchHistory1.setDate_added(timestamp);	    	 
	    	 watchHistory1.setQuiz_result(null);    	 
	    	 watchHistory1.setWatching_lesson_id(mCDto.getLessonId()); 	 
	    	
	    	 watchHistory1.setCompleted_lesson(lessons);  	
	    	 
	    	 watchHistoryRepo.save(watchHistory1);	   

	    	 
	    	 return "Row Inserted";
	    	 
	    	 
	     } else {	
	    	 
	    	
	    	 Section sec  = sectionRepo.findById(mCDto.getSectionId()).orElseThrow(() -> new ResourceNotFound("Course","Id",String.valueOf(mCDto.getSectionId())));
	    	
	    	 List<Integer> secLessons = lessonRepo.findAllBySectionId(mCDto.getSectionId());    			 
	    			 
			 List<Integer> lessons = watchHistory.getCompleted_lesson();
			 
			 boolean check = Arrays.asList(lessons).containsAll(Arrays.asList(secLessons));
			 
			 if(check) {
				 lessons.removeAll(secLessons);
			 }else {
		    	 secLessons.stream().forEach(secLes -> lessons.add(secLes));
		    	
		    	 Set<Integer> newLessons = new HashSet<>();
		    	 
		    	 newLessons.addAll(lessons);
		    	 
		    	 lessons.clear();
		    	 
		    	 lessons.addAll(newLessons);

			 }
			 
	 		 
	    	 WatchHistory watchHistory1 = new WatchHistory();
	    	 
	    	 watchHistory1.setWatching_lesson_id(mCDto.getLessonId()); 	
	    	 
	    	 watchHistory1.setWhid(watchHistory.getWhid());	 
	    	 
	    	 watchHistory1.setCourse(watchHistory.getCourse());
	    	 watchHistory1.setStudent(watchHistory.getStudent());    
	    	 watchHistory1.setDate_added(timestamp);	    	 
	    	 watchHistory1.setQuiz_result(null);    
	    	 
	    	 
	    	 
	    	 watchHistory1.setCompleted_lesson(lessons);  	
	    	 
	    	 watchHistoryRepo.save(watchHistory1);	   
	    	 
	    	 
	    	 System.out.println(lessons);
	    	 
	    	 return "Section Updated!";
	     }
	 
	     
		
		
		
	}

	
	
	

	

}
