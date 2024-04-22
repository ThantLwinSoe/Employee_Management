package com.mit.tls.wrapper;

import com.mit.tls.entity.Employee;
import com.mit.tls.entity.Qualification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EmployeeQualificationWrapper {
	
	private Employee employee;
	private Qualification[] qualification;
	
	
}
