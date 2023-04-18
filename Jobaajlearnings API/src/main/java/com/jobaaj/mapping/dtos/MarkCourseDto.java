package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MarkCourseDto {
	
	private int studentId;
	
	private int lessonId;
	
	private int sectionId;
	
	private int courseId;
	

}
