package com.jobaaj.mapping.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Trainer;


public interface TrainerRepo extends JpaRepository<Trainer, Integer> {
	
	
//	public boolean existsByEmail(String email);
//	
//	
//	
//	public boolean existsByContact(String contact);

}
