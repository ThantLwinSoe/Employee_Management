package com.mit.tls.exception;

import java.io.Serializable;

public class EmployeeNotFoundException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(String text) {
		super(text);
	}
	
	public EmployeeNotFoundException(String text,Throwable cause) {
		super(text, cause);
	}
	
	
	

}
