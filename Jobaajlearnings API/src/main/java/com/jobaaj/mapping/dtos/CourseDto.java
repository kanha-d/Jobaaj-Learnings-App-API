package com.jobaaj.mapping.dtos;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseDto {
	
	private int cid;
	
	private String title;
	private String thumb;
	private long enrolled;
	private String price;
	private String discountedPrice;
	private long review;
	
	private CategoryDto category;
	private TrainerDto trainer;
	
	private List<RatingDto> rating = new ArrayList<>();
	private List<SectionDto> sections = new ArrayList<>();
	
	@JsonIgnore
	private List<StudentDto> students;
	
}
