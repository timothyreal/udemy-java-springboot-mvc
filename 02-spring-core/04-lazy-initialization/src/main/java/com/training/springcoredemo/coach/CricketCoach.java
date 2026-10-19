package com.training.springcoredemo.coach;

import org.springframework.stereotype.Component;

@Component 
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In" + getClass().getSimpleName() + " constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes!";
    }
}
