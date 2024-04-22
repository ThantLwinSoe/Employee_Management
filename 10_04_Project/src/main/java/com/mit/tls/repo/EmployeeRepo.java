package com.mit.tls.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mit.tls.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	@Query("delete from Employee  where id= ?1")
	public void deleteEmployee(int id);
	
	
}
