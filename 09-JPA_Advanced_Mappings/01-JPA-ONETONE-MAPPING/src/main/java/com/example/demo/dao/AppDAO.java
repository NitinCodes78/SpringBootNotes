package com.example.demo.dao;
import com.example.demo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
