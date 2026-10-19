package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.springcoredemo.coach.Coach;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@RestController 
public class DemoController {

    private final Coach _coach;

    public DemoController(@Qualifier ("aquatic") Coach coach) {
        this._coach = coach;
    }

    @PostConstruct 
    public void onStartup() {
        System.out.println("In onStartup: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void dispose() {
        System.out.println("In dispose: " + getClass().getSimpleName());
    }

    @GetMapping ("/dailyworkout")
    public String getDailyWorkout() {
        return this._coach.getDailyWorkout();
    }
}
