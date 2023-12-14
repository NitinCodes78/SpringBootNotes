package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.ArrayList;
//The term "Java POJO" refers to a class, not an object.
@RestController
@RequestMapping("/api")
public class StudentRestController {
    //define endpoint for "/students" - return a list of students
      private List<Student> theStudents;
      //define @PostConstruct to load the student data ... only once!

    @PostConstruct
    public void loadData(){
        theStudents=new ArrayList<>();
        theStudents.add(new Student("Nitin","Goyal"));
        theStudents.add(new Student("Daffy","Duck"));
        theStudents.add(new Student("Mickey","Mouse"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    //define endPoint or "/students/{studentId}" - return student at index

    @GetMapping("students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //just index into the list ... keep it simple for now

        //check the studentId against the list size

        if(studentId>=theStudents.size() || studentId<0){
            throw new StudentNotFoundException("Student id not Found - "+studentId);

            //We have thrown the exception but we now handle the exception using @ExceptionHandler
        }
        return theStudents.get(studentId);
    }
}
