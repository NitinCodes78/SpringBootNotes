package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("In Constructor: "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
