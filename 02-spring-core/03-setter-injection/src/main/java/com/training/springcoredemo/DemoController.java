package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.springcoredemo.coach.Coach;

@RestController 
public class DemoController {

    private Coach _coach;

    @Autowired
    private void setCoach(Coach coach) {
        this._coach = coach;
    }

    @GetMapping ("/dailyworkout")
    public String getDailyWorkout() {
        return this._coach.getDailyWorkout();
    }
}
