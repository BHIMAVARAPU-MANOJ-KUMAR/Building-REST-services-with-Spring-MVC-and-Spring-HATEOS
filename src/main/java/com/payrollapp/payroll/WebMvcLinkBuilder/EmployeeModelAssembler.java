package com.payrollapp.payroll.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.payrollapp.payroll.controller.EmployeeController;
import com.payrollapp.payroll.model.Employee;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{

	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		// TODO Auto-generated method stub
		return EntityModel.of(employee,
				linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
}
