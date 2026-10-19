package com.training.springcoredemo.coach;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component 
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @PostConstruct 
    public void onStartup() {
        System.out.println("In onStartup: " + getClass().getSimpleName());
    }

    @PreDestroy 
    public void dispose() {
        System.out.println("In dispose: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes!";
    }
}
