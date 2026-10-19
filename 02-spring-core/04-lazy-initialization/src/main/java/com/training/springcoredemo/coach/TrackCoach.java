package com.training.springcoredemo.coach;

import org.springframework.stereotype.Component;

@Component 
public class TrackCoach implements Coach {

    public TrackCoach() {
        System.out.println("In" + getClass().getSimpleName() + " constructor");
    }
    
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

}
