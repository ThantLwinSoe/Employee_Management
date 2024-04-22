package com.mit.tls.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.tls.dto.QualificationDto;
import com.mit.tls.entity.Qualification;
import com.mit.tls.exception.QualificationNotFoundException;
import com.mit.tls.mapper.QualificationMapper;
import com.mit.tls.repo.QualificationRepo;

@Service
public class QualificationService {
	
	@Autowired
	private QualificationRepo ro;
	@Autowired
	private QualificationMapper mapper;
	
	// create
	public QualificationDto createQuali(QualificationDto dto) {
		var qual = mapper.getQualificationFromQualificationDto(dto);
		ro.save(qual);
		return mapper.getQualificationDtoFromQualification(qual);
	}
	
	// search by id 
	public QualificationDto searchById(int id) {
		
		Qualification qual;
		try {
		 qual = ro.findById(id).get();
		 
		}catch(Exception e) {
			throw new QualificationNotFoundException("Qualification not found by id!!!");
		}
		return mapper.getQualificationDtoWithoutEmployee(qual);
	}
	
	// get all qualification
	public List<QualificationDto> getAllQuali() {
		
		List<Qualification> qual = ro.findAll();
		return qual.stream().map( (q) -> mapper.getQualificationDtoWithoutEmployee(q)).toList();
		
		
	}
	
	// update
	public QualificationDto updateQuali(int id, QualificationDto dto) {
		
		QualificationDto qDto = searchById(id);
		qDto.setEmployeeList(dto.getEmployeeList());
		qDto.setCourseName(dto.getCourseName());
		qDto.setYear(dto.getYear());
		var qual = mapper.getQualificationFromQualificationDto(qDto);
		ro.save(qual);
		return qDto;		
	}
	
	// Delete
	public Qualification deleteQualiById(int id) {
		
		QualificationDto dto = searchById(id);
		Qualification q = mapper.getQualificationFromQualificationDto(dto);
		
		ro.deleteById(dto.getId());
		return q;
	}

}
