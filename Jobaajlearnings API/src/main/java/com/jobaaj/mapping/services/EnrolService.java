package com.jobaaj.mapping.services;

import com.jobaaj.mapping.dtos.CouponDto;
import com.jobaaj.mapping.dtos.CouponResponse;
import com.jobaaj.mapping.dtos.EnrolCourseDto;



public interface EnrolService {

	
	CouponResponse checkCouponValid(CouponDto couponDto);
	
	
	String enrolCourse(EnrolCourseDto enrolCourseDto);
	
	
	
	
}
