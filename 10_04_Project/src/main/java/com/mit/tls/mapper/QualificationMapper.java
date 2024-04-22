package com.mit.tls.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mit.tls.dto.QualificationDto;
import com.mit.tls.entity.EmployeeCloneWithoutQualificationList;
import com.mit.tls.entity.Qualification;

@Component
public class QualificationMapper {
	
	public Qualification getQualificationFromQualificationDto(QualificationDto dto) {
		return new Qualification(
				dto.getId(), 
				dto.getCourseName(), 
				dto.getYear(),
				dto.getEmployee());
				
	}
	
	
	public QualificationDto getQualificationDtoFromQualification(Qualification qualification) {
		return new QualificationDto(
				qualification.getId(), 
				qualification.getCourseName(), 
				qualification.getYear(), 
				qualification.getEmployee());
	}
	
	public QualificationDto getQualificationDtoWithoutEmployee(Qualification q) {
		return new QualificationDto(q.getId(), q.getCourseName(), q.getYear(), 
				new EmployeeCloneWithoutQualificationList(
						q.getEmployee().getId(),
						q.getEmployee().getName(),
						q.getEmployee().getDob(),
						q.getEmployee().getNrc(),
						q.getEmployee().getMail(),
						q.getEmployee().getAddress(),
						q.getEmployee().getCity(),
						q.getEmployee().getGender(),
						q.getEmployee().getSkill()
						));
	}

}
