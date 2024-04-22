package com.mit.tls.dto;

import java.time.LocalDate;
import java.util.List;

import com.mit.tls.entity.Employee.Gender;
import com.mit.tls.entity.Qualification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
	
	private int id;
	private String name;
	private LocalDate dob;
	private String nrc;
	private String mail;
	private String address;
	private String city;
	private Gender gender;
	
	private List<String> skill;
	
	private List<Qualification> qualifications;
}
