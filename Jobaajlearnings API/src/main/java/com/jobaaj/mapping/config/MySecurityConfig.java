package com.jobaaj.mapping.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jobaaj.mapping.exceptions.JwtAuthenticationEntryPoint;
import com.jobaaj.mapping.serviceimpls.StudentServiceImpl; 

@Configuration
@EnableMethodSecurity
public class MySecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    UserDetailsService userDetailsService() {

//      UserDetails adminUser = User.withUsername("admin").password(passwordEncoder().encode("123")).roles("ADMIN").build();

        return new StudentServiceImpl();

    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf()
		.disable()
		.authorizeHttpRequests()
//		.requestMatchers("/home/admin")
//		.hasRole("ADMIN")
//		.requestMatchers("/home/user")
//		.hasRole("NORMAL")
		.requestMatchers("/token")
		.permitAll()
		.requestMatchers("/users/**")
		.permitAll()
		.requestMatchers("/home/**")
		.permitAll()
		.requestMatchers("/course/**")
		.permitAll()
		.requestMatchers("/student/**")
		.permitAll()
		.requestMatchers("/enrol/**")
		.permitAll()
		.anyRequest()
		.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().accessDeniedPage("/home/error")
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
		
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
		
	}
	
	
	 @Bean
    public AuthenticationManager authenticationManager(UserDetailsService customUserDetailsService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        List<AuthenticationProvider> providers =  List.of(authProvider);

        return new ProviderManager(providers);
    }
	
	
	

}
