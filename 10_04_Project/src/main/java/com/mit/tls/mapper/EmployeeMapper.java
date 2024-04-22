package com.mit.tls.mapper;

import org.springframework.stereotype.Component;

import com.mit.tls.dto.EmployeeDto;
import com.mit.tls.entity.Employee;

@Component
public class EmployeeMapper {
	
	public Employee getEmployeeFromEmployeeDto(EmployeeDto dto) {
		return new Employee(
				dto.getId(), 
				dto.getName(), 
				dto.getDob(), 
				dto.getNrc(), 
				dto.getMail(), 
				dto.getAddress(), 
				dto.getCity(), 
				dto.getGender(), 
				dto.getSkill(), 
				dto.getQualifications());
	}
	
	public EmployeeDto getEmployeeDtoFromEmployee(Employee employee) {
		return new EmployeeDto(
				employee.getId(), 
				employee.getName(), 
				employee.getDob(), 
				employee.getNrc(), 
				employee.getMail(), 
				employee.getAddress(), 
				employee.getCity(), 
				employee.getGender(), 
				employee.getSkill(), 
				employee.getQualifications());
	}

}
