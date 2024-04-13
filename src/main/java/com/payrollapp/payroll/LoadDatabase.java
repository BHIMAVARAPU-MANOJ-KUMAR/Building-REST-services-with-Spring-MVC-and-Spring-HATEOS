package com.payrollapp.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payrollapp.payroll.model.Employee;
import com.payrollapp.payroll.model.Order;
import com.payrollapp.payroll.model.Status;
import com.payrollapp.payroll.repository.EmployeeRepository;
import com.payrollapp.payroll.repository.OrderRepository;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initialDataBase(EmployeeRepository repository, OrderRepository orderRepository) {
		return args -> {
			repository.save(new Employee("Bilbo", "Baggins", "burglar"));
			repository.save(new Employee("Frodo", "Baggins", "thief"));

			repository.findAll().forEach(employee -> log.info("Preloaded " + employee));
			
			orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
			orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

			orderRepository.findAll().forEach(order -> {
				log.info("Preloaded " + order);
			});
		};
	}
}
