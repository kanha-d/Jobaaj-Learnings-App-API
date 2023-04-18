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


public interface SectionRepo extends JpaRepository<Section, Integer> {
	
	
	@Query(value = "SELECT * FROM lesson l WHERE  l.section_id = :id", nativeQuery = true)
	List<Lesson> selectLessons(@Param("id") Integer sectionId);

	
}
