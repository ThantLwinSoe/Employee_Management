package com.mit.tls.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mit.tls.exception.EmployeeNotFoundException;
import com.mit.tls.exception.QualificationNotFoundException;

@RestControllerAdvice
public class BusinessExceptionHandler {
	
	@ExceptionHandler(value = { EmployeeNotFoundException.class})
	public InformationToClient handlerEmployee(EmployeeNotFoundException e) {
		return new InformationToClient(e.getMessage(), HttpStatus.NOT_FOUND) ;
	}
	
	@ExceptionHandler(value = {QualificationNotFoundException.class})
	public InformationToClient handlerQualification(QualificationNotFoundException e) {
		return new InformationToClient(e.getMessage(),HttpStatus.NOT_FOUND );
	}
	
	

}
