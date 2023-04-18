package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RatingDto {
	
	private int rid;
	private UserDto userId;
	private String review;

}
