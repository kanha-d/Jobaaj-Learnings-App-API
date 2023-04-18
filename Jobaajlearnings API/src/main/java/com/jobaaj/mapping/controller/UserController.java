package com.jobaaj.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobaaj.mapping.dtos.StudentDto;
import com.jobaaj.mapping.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	
	@Autowired
	private StudentService studentService;
	
	// Post - Create User
	@PostMapping("/create")
	public ResponseEntity<StudentDto> createUser(@Valid @RequestBody StudentDto StudentDto){
		
		StudentDto createStudentDto = this.studentService.createUser(StudentDto);
		
		return new ResponseEntity<>(createStudentDto,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/verify")
	public ResponseEntity<StudentDto> verifyUser(@Valid @RequestBody StudentDto StudentDto){
		
		StudentDto createStudentDto = this.studentService.emailVerify(StudentDto);
		
		return new ResponseEntity<>(createStudentDto,HttpStatus.CREATED);
		
	}
	
	
	// Post - Create User
	@PostMapping("/login")
	public ResponseEntity<StudentDto> loginUser(@Valid @RequestBody StudentDto StudentDto){
		
		StudentDto loginStudentDto = this.studentService.loginUser(StudentDto);
		
		return new ResponseEntity<>(loginStudentDto,HttpStatus.FOUND);
		
	}
		

	// Get Mapping  - All User
//	@GetMapping("/emp/pros/{userId}")
//	public ResponseEntity<MappingJacksonValue> getEmployeProjects(@PathVariable("userId") Integer userId){
//		
//		
//		EmployeDto employeDto = this.studentService.getEmploye(userId);
//		
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept();
//		FilterProvider filterProvider = new SimpleFilterProvider().setDefaultFilter(filter);		
//		
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeDto);
//		
//		mappingJacksonValue.setFilters(filterProvider);
//		
//		
//		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
//		
//	}
	
	
	
	
	
	

}
