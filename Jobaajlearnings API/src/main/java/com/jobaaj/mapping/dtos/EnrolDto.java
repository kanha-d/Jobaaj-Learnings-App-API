package com.jobaaj.mapping.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EnrolDto {
	
	private int rid;
	private UserDto userId;
	private String review;

}
