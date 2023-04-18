package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	
//	public boolean existsByEmail(String email);
//	
//	
//	
//	public boolean existsByContact(String contact);

}
