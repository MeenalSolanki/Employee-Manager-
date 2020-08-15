package com.mindBrowser.manageremployee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(	name = "employee")
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empid")
	private Long empid;
	
	@NotBlank
	@Column(name="first_name")
	@Size(max = 20)
	private String firstName;
	
	@NotBlank
	@Column(name="last_name")
	@Size(max = 20)
	private String lastName;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Column(name="address")
	@Size(max = 90)
	private String address;
	
	@Column(name="dob")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date dob;
	
	
	@Column(name="mobile_no")
	private String mobileno;
	
	@NotBlank
	@Column(name="city")
	@Size(max = 40)
	private String city;

	
	@ManyToOne
	@JoinColumn(name = "manager_id" ,nullable=false)
	@JsonIgnore
	private Manager manager;
	
	public Employee() {}


	public Employee(String firstName, String lastName, String address, Date dob, String mobileNo, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.mobileno = mobileNo;
		this.city = city;
	}
	
	
}


