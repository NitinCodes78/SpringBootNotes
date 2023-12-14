package com.example.demo.dao;

import com.example.demo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        //This will also save the details object because of cascadeType.ALL
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId){
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
         Instructor tempInstructor=entityManager.find(Instructor.class, theId);
         entityManager.remove(tempInstructor);
    }
}
