package com.training.crud_employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.training.crud_employee.entity.Employee;
import com.training.crud_employee.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service 
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository _employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeDAO) {
        this._employeeRepository = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return this._employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int employeeId) {
        Optional<Employee> result = this._employeeRepository.findById(employeeId);

        Employee dbEmployee = null;
        if (result.isPresent()) {
            dbEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + employeeId);
        }

        return dbEmployee;
    }

    @Transactional 
    @Override
    public Employee save(Employee employee) {
        return this._employeeRepository.save(employee);
    }

    @Transactional 
    @Override
    public void deleteById(int employeeId) {
        this._employeeRepository.deleteById(employeeId);
    }

}
