package com.mit.tls.exception;

public class QualificationNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public QualificationNotFoundException(String message) {
		super(message);
	}
	
	public QualificationNotFoundException(String message,Throwable throwable) {
		super(message, throwable);
	}

}
