package com.training.thymeleafdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.thymeleafdemo.model.Student;



@Controller 
public class StudentController {

    @Value ("${countries}")
    private List<String> countries;

    @Value ("${languages}")
    private List<String> programmingLanguages;

    @Value("${operating-systems}")
    private List<String> operatingSystems;

    @GetMapping ("/showStudentForm")
    public String showFormString(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        
        model.addAttribute("countries", countries);
        model.addAttribute("languages", programmingLanguages);
        model.addAttribute("systems", operatingSystems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent) {
        
        return "student-confirmation";
    }
    
    
}
