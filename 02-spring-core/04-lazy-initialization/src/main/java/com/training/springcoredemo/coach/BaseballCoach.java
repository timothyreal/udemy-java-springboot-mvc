package com.training.springcoredemo.coach;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component 
@Primary 
public class BaseballCoach implements Coach {

    public BaseballCoach() {
        System.out.println("In" + getClass().getSimpleName() + " constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice!";
    }

}
