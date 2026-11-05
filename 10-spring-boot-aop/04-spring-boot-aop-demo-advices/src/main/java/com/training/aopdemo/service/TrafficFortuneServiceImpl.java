package com.training.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service 
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

    @Override
    public String getFortune() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "Expect heavy traffic!";
    }

    @Override
    public String getFortune(boolean forceException) {
        if (forceException) {
            throw new RuntimeException("Accident need to hide!");
        }
        return this.getFortune();
    }
}
