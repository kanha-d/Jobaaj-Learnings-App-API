package com.jobaaj.mapping.dtos;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PasswordDto {
	
	private int studentId;
	
	private String oldpassword;
	
	private String newpasword;
	
	private String confirmpassword;
	
	

}
