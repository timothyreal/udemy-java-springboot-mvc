package com.springbootdemo.demoapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class GreetingController {

    @Value ("${team.name}")
    private String teamName;
    
    @GetMapping ("/")
    public String sayGreeting() {
        return "Hello, World!, from " + teamName;
    }

    @GetMapping ("/hello")
    public String sayNamedGreeting(@RequestParam (name = "name", defaultValue = "World") String theName) {
        return "Hello, " + theName + "!, from " + teamName;
    }
}
