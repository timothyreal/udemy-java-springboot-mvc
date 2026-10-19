package com.training.crud_employee.service;

import java.util.List;

import com.training.crud_employee.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);
}
