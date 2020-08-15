package com.mindBrowser.manageremployee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindBrowser.manageremployee.entity.Employee;
import com.mindBrowser.manageremployee.repository.EmployeeRepository;
import com.mindBrowser.manageremployee.service.StoreCurrentManager;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/mindbowser")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	 @PostMapping("/employees")
	    public Employee addUpdateEmployee(@Valid @RequestBody Employee employees)
	 	{
		 System.out.println("\n\n ---------- Firstname  email "+StoreCurrentManager.getManager().getEmail()
				 +" : "+StoreCurrentManager.getManager().getFirstname());
		 employees.setManager(StoreCurrentManager.getManager());
		
		 // add or update employee
		return employeeRepository.save(employees);
		
	 	}
	
	//delete employee 
	 @DeleteMapping("/employees/{empId}")
	    public ResponseEntity<?> deleteEmployee(@PathVariable Long empId)
	 	{
		 
		 return   employeeRepository.findById(empId).map(employee -> {
			 employeeRepository.delete(employee);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Employee  id  not found"));

	 	}
	 
	  
	
}
