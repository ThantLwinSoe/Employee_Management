package com.mit.tls.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.tls.dto.EmployeeDto;
import com.mit.tls.entity.Employee;
import com.mit.tls.entity.Qualification;
import com.mit.tls.exception.EmployeeNotFoundException;
import com.mit.tls.mapper.EmployeeMapper;
import com.mit.tls.repo.EmployeeRepo;
import com.mit.tls.repo.QualificationRepo;
import com.mit.tls.wrapper.EmployeeQualificationWrapper;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;
	@Autowired
	private EmployeeMapper mapper;
	
	@Autowired
	private QualificationRepo qrepo;
	
	public Employee insertEmployee(EmployeeQualificationWrapper wrapper) {
		
		var emp = repo.save(wrapper.getEmployee());
		var qual = wrapper.getQualification();
		
		
		for(Qualification qualification : qual) {
			qualification.setEmployee(emp);
			qrepo.save(qualification);
		}	
		
		return emp;
	}
	
	
	public EmployeeDto getEmployeeById(int id) {
		Employee employee;
		
		try {
		employee = repo.findById(id).get();
		}catch(Exception e){
			throw new EmployeeNotFoundException("Employee does not exist by id!!!");
		}
		
		return mapper.getEmployeeDtoFromEmployee(employee);
	}
	
	
	public List<EmployeeDto> getAllEmployee() {
		 List<Employee> employees = repo.findAll();
		 return employees.stream().map( (emp) -> mapper.getEmployeeDtoFromEmployee(emp)).toList();
	}
	
	public EmployeeQualificationWrapper converterToWrapperFromEmployee(Employee emp) {
		
		 
		
		return null;
	}
	
	@Transactional
	public Employee updateEmployee(int id , EmployeeQualificationWrapper wrapper) {
		// update Employee 
		Employee emp = wrapper.getEmployee();
		// Update Qualification
		Qualification[] qual = wrapper.getQualification();
		
		// Exiting employee
		EmployeeDto empDto = getEmployeeById(id);
		// original qualification list
		// List<Qualification> original_qual_list =  empDto.getQualifications();
	
		empDto.setAddress(emp.getAddress());
		empDto.setCity(emp.getCity());
		empDto.setDob(emp.getDob());
		empDto.setGender(emp.getGender());
		empDto.setMail(emp.getMail());
		empDto.setName(emp.getName());
		empDto.setNrc(emp.getNrc());
		empDto.setSkill(emp.getSkill());
		empDto.setQualifications(emp.getQualifications());
//		
		Employee entity = mapper.getEmployeeFromEmployeeDto(empDto);
		Employee updatedEmployee = repo.save(entity);
		
		if(qual.length >= 1) { // check qualification exist
			for(Qualification q : qual) {
			
			// get original qualification by id if id exit
				Qualification originalQualification = qrepo.findById(q.getId()).orElse(null);
				
				if(originalQualification != null) {
					originalQualification.setCourseName(q.getCourseName());
					originalQualification.setYear(q.getYear());
					originalQualification.setEmployee(updatedEmployee);
					qrepo.save(originalQualification);
				}else {
					q.setEmployee(updatedEmployee);
					qrepo.save(q);
				}
				
					
			}
		}
//		// for modify
////			for(Qualification q : qual)  {
//				
////				Qualification existingQualification = qrepo.findById(q.getId()).orElse(null);
////				var bool = Arrays.asList(qual).stream()
////						.anyMatch( 
//								(qq) -> qq.getId() == emp.getId());
//				
//				if(bool) {
//					Qualification existingQualification = qrepo.findById(q.getId()).orElse(null);
//					existingQualification.setCourseName(q.getCourseName());
//		            existingQualification.setYear(q.getYear());
//		            qrepo.save(existingQualification);
//				} else {
//					q.setEmployee(updatedEmployee);
//					qrepo.save(q);
//				}
				
//				Qualification existingQualification = qrepo.findById(q.getId()).orElse(null);
//				
//				if(existingQualification != null) {
//					existingQualification.setCourseName(q.getCourseName());
//		            existingQualification.setYear(q.getYear());
//		            qrepo.save(existingQualification);
//				} 
				
//		}		
		
		return updatedEmployee;
	}
	
	public List<EmployeeDto> deleteEmployeeById(int id) {
		
		var dto = getEmployeeById(id);
		
		var list = dto.getQualifications();
		for(var delData : list) {
			qrepo.deleteById(delData.getId());
		}	
		repo.deleteById(id);

		return getAllEmployee();
	}
	

}
