package com.training.springcoredemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.util.Coach;

@RestController 
public class DemoController {

    private final Coach _coach;

    public DemoController(Coach myCoach) {
        this._coach = myCoach;
    }

    @GetMapping ("/dailyworkout")
    public String getDailyWorkout() {
        return this._coach.getDailyWorkout();
    }
}
