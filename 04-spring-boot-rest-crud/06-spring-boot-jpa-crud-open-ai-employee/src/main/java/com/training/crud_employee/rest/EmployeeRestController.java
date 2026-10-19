package com.training.crud_employee.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.crud_employee.entity.Employee;
import com.training.crud_employee.service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;



@RestController 
@RequestMapping ("/api/employees")
public class EmployeeRestController {
    private final EmployeeService _employeeService;
    private final JsonMapper _jsonMapper;

    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this._employeeService = employeeService;
        this._jsonMapper = jsonMapper;
    }

    @GetMapping
    public List<Employee> findAll() {
        return this._employeeService.findAll();
    }

    @GetMapping ("/{employeeId}")
    public Employee getEmployeeByIdEmployee(@PathVariable(name = "employeeId") int theEmployeeId) {
        Employee dbEmployee = this._employeeService.findById(theEmployeeId);

        if (dbEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theEmployeeId);
        }

        return dbEmployee;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);

        Employee dbEmployee = this._employeeService.save(employee);

        return dbEmployee;
    }

    @PutMapping 
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = this._employeeService.save(employee);

        return dbEmployee;
    }
    
    @PatchMapping ("/{employeeId}")
    public Employee updateEmployee(@PathVariable(name = "employeeId") int theEemployeeId, @RequestBody Map<String, Object> employeePatch) {
        Employee foundEmployee = this._employeeService.findById(theEemployeeId);

        if (foundEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theEemployeeId);
        }

        if (employeePatch.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body");
        }

        Employee patchedEmployee = this._jsonMapper.updateValue(foundEmployee, employeePatch);
        Employee dbEmployee = this._employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    @DeleteMapping ("/{employeeId}")
    public String deleteById(@PathVariable(name = "employeeId") int theEmployeeId) {
        Employee tempEmployee = this._employeeService.findById(theEmployeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theEmployeeId);
        }

        this._employeeService.deleteById(theEmployeeId);

        return "Deleted employee id - " + theEmployeeId;
    }
}
