package com.mit.tls.dto;

import java.time.LocalDate;

import com.mit.tls.entity.Employee;
import com.mit.tls.entity.EmployeeCloneWithoutQualificationList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QualificationDto {
	
	private int id;
	private String courseName;
	private LocalDate year;
	private Employee employee;
	
	private EmployeeCloneWithoutQualificationList employeeList;
	
	public QualificationDto(int id, String name, LocalDate date, EmployeeCloneWithoutQualificationList list) {
		this.id = id;
		this.courseName = name;
		this.year = date;
		this.employeeList = list;
	}
	
	public QualificationDto(int id, String name, LocalDate date, Employee employee) {
		this.id = id;
		this.courseName = name;
		this.year = date;
		this.employee = employee;
	}

}
