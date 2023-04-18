package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto {
	
	private int cid;
	private String name;
	private int parent;
	private String slug;
	
}