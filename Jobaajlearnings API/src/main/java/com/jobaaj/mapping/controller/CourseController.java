package com.jobaaj.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobaaj.mapping.dtos.CommentDto;
import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.dtos.UserDto;
import com.jobaaj.mapping.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course/")
public class CourseController {
	
	
	@Autowired
	private CourseService courseService;

	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<CourseDto> getCourse(@PathVariable("courseId") Integer courseId){
		
		//return new ResponseEntity<List<CourseDto>>(this.courseService.listofRecommendedCourses(offset,limit,sort),HttpStatus.OK);
		return new ResponseEntity<CourseDto>(this.courseService.getCourse(courseId),HttpStatus.OK);
			
	}
	
	
	

}
