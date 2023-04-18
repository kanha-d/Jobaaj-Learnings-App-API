package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LessonDto {
	
	private int lid;
	
	private String title;
	
	private String videoUrl;
	
	private int order;
	

}
