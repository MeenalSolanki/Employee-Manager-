package com.mindBrowser.manageremployee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mindBrowser.manageremployee.entity.Employee;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	//to give only the manager added employees data
	Page<Employee> findByManagerId(@Param("id") Long id, Pageable pageable);
}
