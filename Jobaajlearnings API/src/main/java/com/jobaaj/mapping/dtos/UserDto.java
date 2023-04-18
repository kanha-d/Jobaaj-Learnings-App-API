package com.jobaaj.mapping.dtos;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
	
	private int uid;
	
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=6, message = "Password minium 6 charecters!")
	private String password;
	private String gender;
	private String biography;
	private String contact;
	private int verification_code;
	private int status;
	private String login_type;
	private String dob;
	private String skills;
	
	
	private List<CourseDto> user_courses;
	
	
}
