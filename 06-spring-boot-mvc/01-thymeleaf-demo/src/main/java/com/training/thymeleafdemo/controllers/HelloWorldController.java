package com.training.thymeleafdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;



@Controller 
public class HelloWorldController {

    @GetMapping ("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
    
    @PostMapping ("/processForm")
    public String processForm() {
        return "helloworld";
    }

    @PostMapping ("/processFormHttpServletRequest")
    public String requestMethodName(HttpServletRequest request, Model model) {
        String studentName = request.getParameter("studentName");

        studentName = studentName.toUpperCase();

        String result = "Yo! " + studentName;

        model.addAttribute("message", result);

        return "helloworld";
    }
    
    @PostMapping ("/processFormRequestParam")
    public String requestMethodName(@RequestParam String studentName, Model model) {
        studentName = studentName.toUpperCase();

        String result = "Sup! " + studentName + "!";

        model.addAttribute("message", result);

        return "helloworld";
    }
}
