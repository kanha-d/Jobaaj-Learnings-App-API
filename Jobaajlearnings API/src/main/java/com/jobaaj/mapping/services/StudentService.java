package com.jobaaj.mapping.services;

import java.util.List;

import com.jobaaj.mapping.dtos.PasswordDto;
import com.jobaaj.mapping.dtos.StudentDto;



public interface StudentService {

	
	StudentDto createUser(StudentDto user);
	
	StudentDto loginUser(StudentDto user);
	
	StudentDto emailVerify(StudentDto user);
	
	StudentDto getUser(Integer userId);
	
	void deleteUserById(Integer userId);
	
	List<StudentDto> listofUsers();
	
	StudentDto updateUser(StudentDto user,Integer userId);
	
	String updatePassword(PasswordDto passwordDto);
	
}
