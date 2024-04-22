package com.mit.tls.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InformationToClient {
	
	private final HttpStatus status;
	private String message;
	
	public InformationToClient(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
