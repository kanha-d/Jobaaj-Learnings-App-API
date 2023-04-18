package com.jobaaj.mapping.dtos;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String email;
	private String password; 
}
