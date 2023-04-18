package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobaaj.mapping.entity.Category;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Lesson;
import com.jobaaj.mapping.entity.Section;
import com.jobaaj.mapping.entity.Trainer;


public interface LessonRepo extends JpaRepository<Lesson, Integer> {
	
	
	List<Lesson> findBySection(Section section);
	
	@Query(value = "select l.lesson_id from lesson l where l.section_id = :secId",nativeQuery = true)
	List<Integer> findAllBySectionId(int secId);

	
}
