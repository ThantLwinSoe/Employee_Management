package com.mit.tls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.tls.dto.EmployeeDto;
import com.mit.tls.entity.Employee;
import com.mit.tls.service.EmployeeService;
import com.mit.tls.wrapper.EmployeeQualificationWrapper;

@CrossOrigin("*")
@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	
	@PostMapping("create")
	public Employee insertData(
			@RequestBody EmployeeQualificationWrapper employee
			) {	
		
		return service.insertEmployee(employee);
		
	}
	
	@GetMapping("all")
	public List<EmployeeDto> allEmployee(){
		return service.getAllEmployee();
	}
	
	@GetMapping("{id}")
	public EmployeeDto getEmployeeById(@PathVariable("id")int id) {
		return service.getEmployeeById(id);
	}
	
	@DeleteMapping("delete/{id}")
	public List<EmployeeDto> deleteEmployeeById(@PathVariable("id") int id) {
		return service.deleteEmployeeById(id);
	}
	
	@PutMapping("update/{id}")
	public Employee updateEmployeeById(@PathVariable("id") int id,@RequestBody EmployeeQualificationWrapper wrapper) {
		return service.updateEmployee(id, wrapper);
	}

	
}
