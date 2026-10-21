package com.training.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller 
public class DemoController {

    @GetMapping("/")
    public String showLangingPageString() {
        return "landing";
    }
    

    @GetMapping("/employees")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/managers")
    public String showLeadersPage() {
        return "managers";
    }

    @GetMapping("/systems")
    public String showSystemsPage() {
        return "systems";
    }
}
