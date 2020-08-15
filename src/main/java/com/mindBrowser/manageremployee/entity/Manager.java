package com.mindBrowser.manageremployee.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(	name = "manager", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "email") 
		})
@Getter
@Setter
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotBlank
	@Size(max = 30)
	private String firstname;
	
	@NotBlank
	@Size(max = 40)
	private String lastname;
	
	@NotBlank
	@Size(max = 90)
	private String address;
	

	@JsonFormat(pattern="MM/dd/yyyy")
	private Date dob;
	
	@NotBlank
	@Size(max = 50)
	private String company;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="manager")
	private Set<Employee> employees;
	
	public Manager()
	{}

	public Manager(String email, String password, String firstname, String lastname, String address, Date dob,
			String company) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.dob = dob;
		this.company = company;
	}
	
	

}
