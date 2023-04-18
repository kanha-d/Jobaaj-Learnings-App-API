package com.jobaaj.mapping.dtos;


import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonFilter("prosremove")
public class EmployeDto {
	
	private int eid;
	private String name;
	

	private List<ProjectDto> pros;
	
}