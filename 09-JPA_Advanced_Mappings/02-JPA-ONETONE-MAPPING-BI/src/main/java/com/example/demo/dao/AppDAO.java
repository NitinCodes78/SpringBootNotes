package com.example.demo.dao;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}