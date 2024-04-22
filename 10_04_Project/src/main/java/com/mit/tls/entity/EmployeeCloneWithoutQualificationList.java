package com.mit.tls.entity;

import java.time.LocalDate;
import java.util.List;

import com.mit.tls.entity.Employee.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeCloneWithoutQualificationList {
	
	private int id;
	private String name;
	private LocalDate dob;
	private String nrc;
	private String mail;
	private String address;
	private String city;
	private Gender gender;
	private List<String> skill;

}
