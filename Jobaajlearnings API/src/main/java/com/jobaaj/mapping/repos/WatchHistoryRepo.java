package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Category;
import com.jobaaj.mapping.entity.WatchHistory;


public interface WatchHistoryRepo extends JpaRepository<WatchHistory, Integer> {
	
	
	WatchHistory findByCourseAndStudent(int CourseId,int studentId);

}
