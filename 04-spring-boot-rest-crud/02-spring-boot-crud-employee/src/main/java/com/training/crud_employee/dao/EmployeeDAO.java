package com.training.crud_employee.dao;

import java.util.List;

import com.training.crud_employee.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);
}
