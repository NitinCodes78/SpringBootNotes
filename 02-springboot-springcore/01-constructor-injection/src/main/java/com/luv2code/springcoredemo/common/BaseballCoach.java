package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    public BaseballCoach(){
        System.out.println(getClass().getSimpleName());
    }
    public String getDailyWorkout(){
        return "Spend 30 minutes in Batting practice";
    }

}
