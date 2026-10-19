package com.training.springcoredemo.coach;

import org.springframework.stereotype.Component;

@Component 
public class TennisCoach implements Coach {

    public TennisCoach() {
        System.out.println("In" + getClass().getSimpleName() + " constructor");
    }
    
    @Override 
    public String getDailyWorkout() {
        return "Practice your backhand volley for 30 minutes!";
    }
}
