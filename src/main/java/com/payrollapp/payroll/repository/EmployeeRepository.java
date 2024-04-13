package com.payrollapp.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrollapp.payroll.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
