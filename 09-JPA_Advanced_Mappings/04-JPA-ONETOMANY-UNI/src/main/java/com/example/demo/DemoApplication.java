package com.example.demo;

import java.util.*;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//             createCourseAndReviews(appDAO);
//             retreiveCoursesAndReviews(appDAO);
             deleteCourseAndReviews(appDAO);

        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO){
        int theId=10;
        //Will delete Course and associated reviews, thanks to CascadeType.All
        appDAO.deleteCourseById(theId);
        System.out.println("Successfully deleted course and the corresponding reviews");
    }
    private void retreiveCoursesAndReviews(AppDAO appDAO) {
        int theId = 10;
        Course course = appDAO.findCourseAndReviewsByCourseId(theId);
        System.out.println(course);
        System.out.println(course.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course tempCourse = new Course("Pacman: How to Score miliion");
        tempCourse.addReview(new Review("Loved it"));
        tempCourse.addReview(new Review("Cool Course"));
        tempCourse.addReview(new Review("Just Okayish"));
        tempCourse.addReview(new Review("teaching style is quite bad"));

        //save the course ... and leverage the cascasde all
        System.out.println("Saving the course");
        System.out.println(tempCourse.getReviews());
        appDAO.save(tempCourse);
    }

    private void deleteCourseById(AppDAO appDAO) {
        int theId = 10;
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        Course tempCourse = appDAO.findCourseById(theId);
        tempCourse.setTitle("Hey, it's the story of my life");
        appDAO.update(tempCourse);
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        tempInstructor.setLastName("TESTER");
        appDAO.update(tempInstructor);
        System.out.println("Update Successfull");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println(tempInstructor);
        System.out.println(tempInstructor.getCourses());
    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        //Here in the instructor class we had set the fetch type to lazy, so courses weren't loaded by default to access them using .getCourses. So we had to find courses using sql query and then later set them
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println(tempInstructor);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        tempInstructor.setCourses(courses);
        System.out.println(tempInstructor.getCourses());

    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        //Here we had set the fetchType to eager
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);

        //We had to set the fetchType to eager for this to work because it didn't load courses by default so .getCouses didn't work. We have to write a query for that
        System.out.println("The Associated Courses: " + tempInstructor.getCourses());
        System.out.println("Done");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan@luv2code.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.susan.com/youtube", "cat food!!!");

        //associate the Objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("Data Structures and ALgorithms in Java");
        Course tempCourse3 = new Course("Introduction to CPP");

        //add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);
        tempInstructor.add(tempCourse3);

        //Note: this will also save the courses because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The Courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Deleting instructor detail id: " + theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");

    }

    private void findInstructorDetail(AppDAO appDAO) {
        //get the instructorDetail object

        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        //print the instructor detail

        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        //print the associated instructor
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
        System.out.println("Done");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("Associated instructorDetail: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

        Instructor tempInstructor = new Instructor("cat", "Sharma", "sharma@luv2code.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.catcode.com/youtube", "cat food!!!");

        //associate the Objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //This will also save the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

    }
}
