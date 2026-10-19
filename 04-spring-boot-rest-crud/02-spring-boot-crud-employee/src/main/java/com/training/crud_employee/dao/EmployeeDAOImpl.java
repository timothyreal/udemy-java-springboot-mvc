package com.training.crud_employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.crud_employee.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository 
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EntityManager _entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> employeeQuery = this._entityManager.createQuery("FROM Employee", Employee.class);

        List<Employee> employees = employeeQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int employeeId) {
        Employee employee = this._entityManager.find(Employee.class, employeeId);

        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = this._entityManager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteById(int employeeId) {
        Employee employee = this.findById(employeeId);

        this._entityManager.remove(employee);
    }

}
