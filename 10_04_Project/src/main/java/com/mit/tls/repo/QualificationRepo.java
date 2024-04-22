package com.mit.tls.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mit.tls.entity.Qualification;

public interface QualificationRepo extends JpaRepository<Qualification, Integer> {
	
	
}
