package com.training.crud_employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.crud_employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
