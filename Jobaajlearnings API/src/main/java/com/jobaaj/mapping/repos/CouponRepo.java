package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobaaj.mapping.entity.Category;
import com.jobaaj.mapping.entity.Coupon;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Trainer;


public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	
	Coupon findByCode(String code);
	
}
