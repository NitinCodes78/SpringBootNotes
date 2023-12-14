package com.luv2code.cruddemo;
import java.util.List;
import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{
			createStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByFirstName(studentDAO);
//			 updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO){
		int num=studentDAO.deleteAll();
		System.out.println(num+" no. of enteries deleted");
	}
	private void deleteStudent(StudentDAO studentDAO){
		int studentId=3;
		studentDAO.delete(studentId);
	}
	private void updateStudent(StudentDAO studentDAO){
		//retrieve student based on the id: primary key
         int studentId=1;
		 Student myStudent=studentDAO.findById(studentId);

		//change first name to "Scooby Dobby"

		myStudent.setFirstName("Scooby Dooby");

		//update the student

		studentDAO.updateIt(myStudent);
		//display the updated element

	}
	private void queryForStudentsByFirstName(StudentDAO studentDAO){
		List<Student> theStudents=studentDAO.findByFirstName("Daffy");

		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
	}}
	private void queryForStudents(StudentDAO studentDAO){

		//get a list of students

		List<Student> theStudents=studentDAO.findAll();
		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}

}
	private void readStudent(StudentDAO studentDAO){

		//create a student object
           System.out.println("Createing a new student object....");
		   Student tempStudent=new Student("Daffy", "duck", "daffy2@gmail.com");
		//save the student

		   System.out.println("Saving the Student...");
		   studentDAO.save(tempStudent);
		//display id of the saved student

		   int theId=tempStudent.getId();
		   System.out.println("Saved Student: "+theId);
		//retreive student based on the id: primary key

		   Student myStudent=studentDAO.findById(theId);
		//display student
		   System.out.println(myStudent);

	}


//	Entity Class: Java Class that is mapped to a database table, must be annotated with @Entity, must have a public or protected no-argument constructor, the class can have other constructors

	private void createStudent(StudentDAO studentDAO){

		//create the student object
		System.out.println("Creating new Student Object......");
		Student tempStudent=new Student("Cat", "Doe", "paul@gmail.com");
		/*save the student object*/System.out.println("Saving the student .....");
		studentDAO.save(tempStudent);
		//display id of the saved student
		int theId=tempStudent.getId();
		System.out.println("Saved student and the id is"+theId);

		//retreive student based on the id: primary key

	}
}
