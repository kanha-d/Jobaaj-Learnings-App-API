package com.jobaaj.mapping.dtos;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentDto {
	
	private int id;
	
	private String content;
	
	private List<CourseDto> courses;

}
