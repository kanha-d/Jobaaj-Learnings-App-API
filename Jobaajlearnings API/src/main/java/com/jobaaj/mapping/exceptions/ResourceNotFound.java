package com.jobaaj.mapping.exceptions;

import lombok.Data;

@Data
public class ResourceNotFound extends RuntimeException{

	String resourceName;
	String filedName;
	String fieldValue;
	
	public ResourceNotFound(String resourceName, String filedName, String fieldValue) {
		
		super(String.format("%s not found with %s : %s", resourceName,filedName,fieldValue));
		
		
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}
	
	
}
