package com.payrollapp.payroll.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
		// TODO Auto-generated constructor stub
		super("Could not find Employee with this ID :- " + id);
	}
}
