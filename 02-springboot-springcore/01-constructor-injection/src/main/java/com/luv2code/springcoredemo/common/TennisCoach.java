package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    public TennisCoach(){
        System.out.println(getClass().getSimpleName());
    }

    public String getDailyWorkout(){
        return "Play tennis daily";
    }
}
