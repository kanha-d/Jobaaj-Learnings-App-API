package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobaaj.mapping.entity.Category;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Trainer;


public interface CourseRepo extends JpaRepository<Course, Integer> {
	
	
	List<Course> findByTitleContaining(String title);
	
	Page<Course> findAllByStatus(Pageable page,int status);
	
	List<Course> findByTrainerAndStatus(Trainer trainer,int status);
	
	Course findByCidAndStatus(int id,int status);

	
//	List<Course> findByUser(User user);

	List<Course> findByCategory(Category category);

	@Query(value = "SELECT * FROM course c WHERE  c.course_id in ( select e.course_id from enrol e where e.s_id = :id)", nativeQuery = true)
	List<Course> selectEnrolledStudents(@Param("id") Integer studentId);

	
	@Query(value = "SELECT * FROM watch_histories w WHERE  w.course_id :lessonId", nativeQuery = true)
	void markLessonByCourse(@Param("lessonId") Integer lessonId);
	
	
}
