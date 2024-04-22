package com.mit.tls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mit.tls.mapper.EmployeeMapper;
import com.mit.tls.service.EmployeeService;
import com.mit.tls.wrapper.EmployeeQualificationWrapper;

//@RestController
//@RequestMapping("wrap")

/**
 * Not Use For Now
 */
public class WrapperController {
	
//	@Autowired
//	EmployeeService service;
//	@Autowired
//	EmployeeMapper mapper;
//	
//	
//	@PostMapping("create")
//	public RedirectView createWraperController(@RequestBody EmployeeQualificationWrapper wrapper) {
//		service.insertEmployee(mapper.getEmployeeDtoFromEmployee(wrapper.getEmployee()));
//		return new RedirectView("http://localhost:8080/qualification/create");
//	}

}
