package com.mit.tls.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate dob;
	
	@Column(nullable = false)
	private String nrc;
	
	@Column(nullable = false)
	private String mail;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String city;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	@Column( columnDefinition = "text[]")
	private List<String> skill;
	
	@JsonManagedReference
	@OneToMany(
				mappedBy = "employee",
				cascade = CascadeType.MERGE
			)
	private List<Qualification> qualifications;
	
	
	public enum Gender {
		MALE,FEMALE
	}
	

}
