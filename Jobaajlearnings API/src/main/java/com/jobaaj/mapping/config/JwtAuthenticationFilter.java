package com.jobaaj.mapping.config;

import java.io.IOException;

import org.hibernate.grammars.hql.HqlParser.SecondContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jobaaj.mapping.serviceimpls.StudentServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private JwtUtill jwtUtill;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Get JWT 
		//Is they Bearer 
		//Validate
		
		String requestTokenHeader =  request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		
		//checking null & formate
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
				username = this.jwtUtill.getUsenameFromToken(jwtToken);
				
			}catch (Exception e) {
				System.out.println("Exetption of GetPassword");
				e.printStackTrace();
			}
			
			
			
		  UserDetails userDetails = this.studentServiceImpl.loadUserByUsername(username);
			
			 
			//Secruity
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Token is not validated!");
			}
			
			
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
