package com.springbootdemo.demoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class GreetingController {
    
    @GetMapping ("/")
    public String sayGreeting() {
        return "Hello, World!";
    }
}
