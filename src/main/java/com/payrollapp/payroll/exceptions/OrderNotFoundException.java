package com.payrollapp.payroll.exceptions;

public class OrderNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		super("Could not find the Order with the ID:- " + id);
	}
}
