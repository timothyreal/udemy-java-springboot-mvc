package com.training.crud_employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.crud_employee.entity.Employee;
import com.training.crud_employee.service.EmployeeService;


@Controller 
@RequestMapping ("/employees")
public class EmployeeController {

    private final EmployeeService _employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this._employeeService = employeeService;
    }

    @GetMapping ("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = this._employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping ("/addEmployeeForm")
    public String addEmployeeForm(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployeeString(@ModelAttribute ("employee") Employee theEmployee) {

        this._employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping ("/updateEmplyeeForm")
    public String updateEmployee(@RequestParam ("employeeId") Integer id, Model model) {
        Employee employee = this._employeeService.findById(id);

        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam ("employeeId") Integer id) {
        this._employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
    
}
