package com.payrollapp.payroll.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	private String lastName;
	private String role;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	public void setName(String name) {
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (!(o instanceof Employee))
	      return false;
	    Employee employee = (Employee) o;
	    return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
	        && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.id, this.firstName,this.lastName, this.role);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Employee{" + "id=" + this.id + ", name='" + this.firstName  + '\'' + ", lastName='" + this.lastName+ '\'' + ", role='" + this.role + '\'' + '}';
	}
}
