package com.training.crud_employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.crud_employee.dao.EmployeeDAO;
import com.training.crud_employee.entity.Employee;

import jakarta.transaction.Transactional;

@Service 
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO _employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this._employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return this._employeeDAO.findAll();
    }

    @Override
    public Employee findById(int employeeId) {
        return this._employeeDAO.findById(employeeId);
    }

    @Transactional 
    @Override
    public Employee save(Employee employee) {
        return this._employeeDAO.save(employee);
    }

    @Transactional 
    @Override
    public void deleteById(int employeeId) {
        this._employeeDAO.deleteById(employeeId);
    }

}
