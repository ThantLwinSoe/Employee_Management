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

import com.mit.tls.dto.QualificationDto;
import com.mit.tls.entity.Qualification;
import com.mit.tls.service.QualificationService;

@CrossOrigin("*")
@RestController
@RequestMapping("qualification")
public class QualificationController {
	
	@Autowired
	private QualificationService service;
	
	@PutMapping("update/{id}")
	public QualificationDto updateQualificationById(
			@PathVariable("id") int id,
			@RequestBody QualificationDto qualification
			) {
		return service.updateQuali(id, qualification);
	}
	
	@GetMapping("all")
	public List<QualificationDto> getAllQualifications() {
		List<QualificationDto> result =  service.getAllQuali();
		return result;
	}
	
	@GetMapping("{id}")
	public QualificationDto serchQualificationById(@PathVariable("id") int id) {
		return service.searchById(id);
	}
	
	@DeleteMapping("delete/{id}")
	public Qualification deleteQualificationById(@PathVariable("id") int id) {
		Qualification qq = service.deleteQualiById(id);
		return qq;
	}	
	
}
