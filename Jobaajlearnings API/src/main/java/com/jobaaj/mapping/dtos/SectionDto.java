package com.jobaaj.mapping.dtos;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SectionDto {
	
	private int sid;
	private String title;
	private int order;
	
	private List<LessonDto> lessons = new ArrayList<>();

}
