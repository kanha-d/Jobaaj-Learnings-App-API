package com.jobaaj.mapping.services;

import java.util.List;

import com.jobaaj.mapping.dtos.CommentDto;
import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.dtos.MarkCourseDto;



public interface CourseService {

	
	CourseDto createCourse(CourseDto user);
	
	CourseDto getCourse(Integer courseId);
	
	
	List<CourseDto> listofRecommendedCourses(Integer offset, Integer limit,String sort);
	
	
	List<CourseDto> listofPopularCourses(Integer offset, Integer limit);
	
	List<CourseDto> searchCourse(String keyword);
	
	List<CourseDto> listofTrainerCourses(Integer trainerId);
	
	List<CourseDto> listofCategoryCourses(Integer catId);
	
	List<CourseDto> listofStudentCourses(Integer userId);
	
	
	String markLesson(MarkCourseDto markCourseDto);
	
	String markSection(MarkCourseDto markCourseDto);
	
	
	
	

	
}
