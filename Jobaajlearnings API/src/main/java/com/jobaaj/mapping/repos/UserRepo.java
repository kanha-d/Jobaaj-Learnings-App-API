package com.jobaaj.mapping.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
	public boolean existsByContact(String contact);

}
