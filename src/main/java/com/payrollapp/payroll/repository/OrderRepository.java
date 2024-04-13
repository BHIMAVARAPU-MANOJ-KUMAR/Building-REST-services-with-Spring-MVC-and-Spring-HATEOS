package com.payrollapp.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrollapp.payroll.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
