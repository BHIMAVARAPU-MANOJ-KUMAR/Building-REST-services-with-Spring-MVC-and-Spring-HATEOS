package com.payrollapp.payroll.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payrollapp.payroll.exceptions.EmployeeNotFoundException;
import com.payrollapp.payroll.model.Employee;
import com.payrollapp.payroll.repository.EmployeeRepository;
import com.payrollapp.payroll.WebMvcLinkBuilder.EmployeeModelAssembler;

@RestController
public class EmployeeController {
	private final EmployeeRepository employeeRepository;
	
	private final EmployeeModelAssembler employeeModelAssembler;

	public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
		this.employeeRepository = employeeRepository;
		this.employeeModelAssembler = employeeModelAssembler;
	}
	
//	@PostMapping("/employees")
//	public Employee newEmployee(@RequestBody Employee employee) {
//		return employeeRepository.save(employee);
//	}
	
	@PostMapping("/employees")
	ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

		EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeRepository.save(newEmployee));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
//	@GetMapping("/employees")
//	public List<Employee> all(){
//		return employeeRepository.findAll();
//	}
	
//	@GetMapping("/employees")
//	CollectionModel<EntityModel<Employee>> all() {
//
//	  List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
//	      .map(employee -> EntityModel.of(employee,
//	          linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
//	          linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
//	      .collect(Collectors.toList());
//
//	  return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
//	}
	
	@GetMapping("/employees")
	public CollectionModel<EntityModel<Employee>> all() {

	  List<EntityModel<Employee>> employees = employeeRepository.findAll().stream() //
	      .map(employeeModelAssembler::toModel)
	      .collect(Collectors.toList());

	  return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
//	@GetMapping("/employees/{id}")
//	public Employee one(@PathVariable Long id) {
//	    
//	    return employeeRepository.findById(id)
//	      .orElseThrow(() -> new EmployeeNotFoundException(id));
//	}
	
//	@GetMapping("/employees/{id}")
//	EntityModel<Employee> one(@PathVariable Long id) {
//
//	  Employee employee = employeeRepository.findById(id) //
//	      .orElseThrow(() -> new EmployeeNotFoundException(id));
//
//	  return EntityModel.of(employee, //
//	      linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//	      linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
//	}
//	
	
	@GetMapping("/employees/{id}")
	public EntityModel<Employee> one(@PathVariable Long id) {

	  Employee employee = employeeRepository.findById(id) //
	      .orElseThrow(() -> new EmployeeNotFoundException(id));

	  return employeeModelAssembler.toModel(employee);
	}
	
//	@PutMapping("/employees/{id}")
//	  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//	    
//	    return employeeRepository.findById(id)
//	      .map(employee -> {
//	        employee.setName(newEmployee.getName());
//	        employee.setRole(newEmployee.getRole());
//	        return employeeRepository.save(employee);
//	      })
//	      .orElseGet(() -> {
//	        newEmployee.setId(id);
//	        return employeeRepository.save(newEmployee);
//	      });
//	  }

	@PutMapping("/employees/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		Employee updatedEmployee = employeeRepository.findById(id) //
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return employeeRepository.save(employee);
				}) //
				.orElseGet(() -> {
					newEmployee.setId(id);
					return employeeRepository.save(newEmployee);
				});

		EntityModel<Employee> entityModel = employeeModelAssembler.toModel(updatedEmployee);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
//	  @DeleteMapping("/employees/{id}")
//	  void deleteEmployee(@PathVariable Long id) {
//	    employeeRepository.deleteById(id);
//	  }
	
	@DeleteMapping("/employees/{id}")
	ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

		employeeRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
