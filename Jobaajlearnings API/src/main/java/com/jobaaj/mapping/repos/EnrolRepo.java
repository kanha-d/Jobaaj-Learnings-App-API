package com.jobaaj.mapping.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Enrol;


public interface EnrolRepo extends JpaRepository<Enrol, Integer> {
	
	long countByCourseId(int courseId);
	
	
	boolean existsBysIdAndCourseId(int sId,int courseId);
	
//	public boolean existsByEmail(String email);
//	
//	
//	
//	public boolean existsByContact(String contact);

}
