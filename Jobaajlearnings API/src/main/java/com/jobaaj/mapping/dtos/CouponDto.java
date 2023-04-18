package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CouponDto {
	
	private String code;
	private int studentId;
	private int courseId;
	
}