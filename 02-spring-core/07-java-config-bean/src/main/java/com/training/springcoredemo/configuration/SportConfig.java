package com.training.springcoredemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.springcoredemo.coach.SwimCoach;

@Configuration
public class SportConfig {

    @Bean("aquatic") SwimCoach swimCoach() {
        return new SwimCoach();
    }
}
