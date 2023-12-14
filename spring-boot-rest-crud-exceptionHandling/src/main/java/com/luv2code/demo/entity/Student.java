package com.luv2code.demo.entity;

public class Student {
    private String firstName;
    private String lastName;
    public Student(){

    }
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //setters and getters are important as they are used by jackson to convert from json to pojo and vice verca
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
