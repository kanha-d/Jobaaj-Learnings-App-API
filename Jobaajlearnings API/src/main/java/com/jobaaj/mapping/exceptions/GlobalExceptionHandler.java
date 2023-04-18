package com.jobaaj.mapping.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobaaj.mapping.dtos.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExecptionHandler(ResourceNotFound ex){
		
		String messageString = ex.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(messageString,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ApiResponse> duplicatEntryExecptionHandler(DuplicateEntryException ex){
		
		String messageString = ex.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(messageString,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		
		Map<String, String> responseMap = new HashMap<>();
		
		
		 ex.getBindingResult().getAllErrors().forEach((error)->{
			 String fieldName = ((FieldError)error).getField();
			 String msg = error.getDefaultMessage();
			 
			 responseMap.put(fieldName, msg);
			 
		 });
		
		return new ResponseEntity<Map<String, String>>(responseMap,HttpStatus.NOT_FOUND);
		
	}
	
	

}
