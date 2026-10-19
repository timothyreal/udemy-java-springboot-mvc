package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.springcoredemo.coach.Coach;


@RestController 
public class DemoController {

    private Coach _coach;
    private Coach _anotherCoach;

    public DemoController(
        @Qualifier ("cricketCoach") Coach coach,
        @Qualifier ("cricketCoach") Coach anotherCoach
    ) {
        this._coach = coach;
        this._anotherCoach = anotherCoach;
    }

    @GetMapping ("/dailyworkout")
    public String getDailyWorkout() {
        return this._coach.getDailyWorkout();
    }

    @GetMapping("check")
    public String check() {
        return "Comparing the two coach objects: " + (this._coach == this._anotherCoach);
    }
    
}
