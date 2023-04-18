package com.jobaaj.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobaaj.mapping.dtos.ApiResponse;
import com.jobaaj.mapping.dtos.CouponDto;
import com.jobaaj.mapping.dtos.CouponResponse;
import com.jobaaj.mapping.dtos.EnrolCourseDto;
import com.jobaaj.mapping.serviceimpls.EnrolServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrol/")
public class EnrolController {

	@Autowired
	EnrolServiceImpl enrolServiceImpl; 
	
	
	
	@PostMapping("/couponCheck")
	public ResponseEntity<CouponResponse> couponAvilablity(@Valid @RequestBody CouponDto couponDto) {

		
		CouponResponse couponResponse  = this.enrolServiceImpl.checkCouponValid(couponDto);
		
		return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.OK);
		

	}
	
	
	@PostMapping("/enrolStudent")
	public ResponseEntity<ApiResponse> enrolStudentAndCourse(@Valid @RequestBody EnrolCourseDto enrolCourseDto) {

		
		String response  = this.enrolServiceImpl.enrolCourse(enrolCourseDto);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(response,true), HttpStatus.OK);
		

	}
	

}
