package com.jobaaj.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobaaj.mapping.config.JwtUtill;
import com.jobaaj.mapping.dtos.JwtAuthRequest;
import com.jobaaj.mapping.dtos.JwtAuthResponse;
import com.jobaaj.mapping.exceptions.ApiException;
import com.jobaaj.mapping.services.StudentService;


@RestController
public class AuthController {
	
	
	@Autowired
	private JwtUtill jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentService userService;
	
	@PostMapping("/token")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		
			try {
				this.authenticate(request.getEmail(),request.getPassword());
			
			} catch (UsernameNotFoundException e) {
			 	e.printStackTrace();
				throw new Exception("No User with this Email");
			} catch (BadCredentialsException e) {
				throw new Exception("Bad Credentials");
			}
			
			UserDetails user = this.userDetailsService.loadUserByUsername(request.getEmail());
			
			String generatedTokenString = jwtTokenHelper.generateToken(user);
			
			JwtAuthResponse response = new JwtAuthResponse();
			response.setToken(generatedTokenString);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}



	private void authenticate(String email, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);

		
		try {
		
		this.authenticationManager.authenticate(authenticationToken); 
		
		}catch (BadCredentialsException e) {
			
			
			 throw new ApiException("Invalid username or password!");
		}
		
		
	}
	
//	//Regeter new User
//	@PostMapping("/register")
//	private ResponseEntity<UserDto> registerUser(@RequestBody StudentDto userDto){
//		
//		UserDto registeredUserDto =  this.userService.registerUser(userDto);
//		
//		return new ResponseEntity<UserDto>(registeredUserDto,HttpStatus.CREATED);
//		
//	}

	
	
}
