package com.jobaaj.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/home/")
public class HomeController {
	
	
	@Autowired
	private CourseService courseService;
	
		
	// Get Mapping  - All recommended Course
	@GetMapping("/recommended/{offset}/{limit}/{sort}")
	public ResponseEntity<List<CourseDto>> getRecommandedCourses(@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit,@PathVariable("sort") String sort){
		
		return new ResponseEntity<List<CourseDto>>(this.courseService.listofRecommendedCourses(offset,limit,sort),HttpStatus.OK);
			
	}
	
	@GetMapping("/public")
	public ResponseEntity<String> publicMsg(){
		
		return new ResponseEntity<String>("Kya bat he dost",HttpStatus.OK);
			
	}
	@GetMapping("/error")
	public ResponseEntity<String> errorMsg(){
		
		return new ResponseEntity<String>("Error Access Denied",HttpStatus.OK);
			
	}
	
	
	@GetMapping("/admin")
	public ResponseEntity<String> adminMsg(){
		
		return new ResponseEntity<String>("Kya bat he admin",HttpStatus.OK);
			
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<String> userMsg(){
		
		return new ResponseEntity<String>("Kya bat he user",HttpStatus.OK);
			
	}
	
	
	// Get Mapping  - All recommended Course
	@GetMapping("/popular/{offset}/{limit}")
	public ResponseEntity<List<CourseDto>> getPopularCourses(
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit){
		
		return new ResponseEntity<List<CourseDto>>(this.courseService.listofPopularCourses(offset, limit),HttpStatus.OK);
			
	}
	
	// Get Mapping  - All recommended Course
	@GetMapping("/course/{courseId}")
	public ResponseEntity<CourseDto> getCourse(
			@PathVariable("courseId") Integer courseId){
		
		return new ResponseEntity<CourseDto>(this.courseService.getCourse(courseId),HttpStatus.OK);
			
	}
	
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<CourseDto>> searchCourse(
			@PathVariable("keyword") String keyword){
		
		return new ResponseEntity<List<CourseDto>>(this.courseService.searchCourse(keyword),HttpStatus.OK);
			
	}
	
	
	@GetMapping("/trainer/{trainer}")
	public ResponseEntity<List<CourseDto>> getTrainerCourse(
			@PathVariable("trainer") Integer trainerId){
		
		return new ResponseEntity<List<CourseDto>>(this.courseService.listofTrainerCourses(trainerId),HttpStatus.OK);
			
	}
	
	@GetMapping("/category/{cat}")
	public ResponseEntity<List<CourseDto>> getCategoryCourse(
			@PathVariable("cat") Integer catId){
		
		return new ResponseEntity<List<CourseDto>>(this.courseService.listofCategoryCourses(catId),HttpStatus.OK);
			
	}
	
	

}
