package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EnrolCourseDto {
	
	private String payId;
	private int studentId;
	private int courseId;
	private Float amount;
	

}
