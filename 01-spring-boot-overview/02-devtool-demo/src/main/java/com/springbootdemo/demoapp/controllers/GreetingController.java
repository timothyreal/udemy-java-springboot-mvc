package com.springbootdemo.demoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class GreetingController {
    
    @GetMapping ("/")
    public String sayGreeting() {
        return "Hello, World!";
    }

    @GetMapping ("/hello")
    public String sayNamedGreeting(@RequestParam (name = "name", defaultValue = "World") String theName) {
        return "Hello, " + theName + "!";
    }
}
