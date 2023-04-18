package com.jobaaj.mapping.exceptions;

import lombok.Data;

@Data
public class DuplicateEntryException extends RuntimeException{

	String resourceName;
	String filedName;
	
	public DuplicateEntryException(String resourceName, String filedName) {
		
		super(String.format("%s Already Exist:  %s", resourceName,filedName));
		
		this.resourceName = resourceName;
		this.filedName = filedName;
	}
	
	
}
