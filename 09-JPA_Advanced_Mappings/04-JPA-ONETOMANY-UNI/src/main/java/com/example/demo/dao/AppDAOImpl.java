package com.example.demo.dao;

import java.util.*;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        //This will also save the details object because of cascadeType.ALL
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }
        //Since we are deleting the instructor, we need to set the instructor for all the courses as null since it's the foreign key in the course table, it will give an exception if the foreign key don't exist

        //Here, we are only deleting the instructor and not the associated course based on our cascade types
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //It also deletes the associated instructor due to bidirectional relation and CascadeType.All
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:data", Course.class);
        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        //This code will retreive Instructor and Courses
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i" + "  Join FETCH i.courses" + " where i.id=:data", Instructor.class);
        //here i-is an alias, short name for instructor, the JOIN FETCH is similar to Eager loading
        query.setParameter("data", theId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //Since the fetch type is lazy, we are telling here in the query to fetch the reviews along with the course
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + " JOIN FETCH c.reviews " + "where c.id=:data", Course.class);
        query.setParameter("data", theId);
        Course course = query.getSingleResult();
        return course;
    }
}
