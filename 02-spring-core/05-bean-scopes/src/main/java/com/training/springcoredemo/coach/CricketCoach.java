package com.training.springcoredemo.coach;

import org.springframework.stereotype.Component;

@Component 
// @Scope (ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes!";
    }
}
