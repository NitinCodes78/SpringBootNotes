package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
//			createInstructor(appDAO);
//			findInstructor(appDAO);
			deleteInstructor(appDAO);

		};
	}

	private void deleteInstructor(AppDAO appDAO){
		int theId=1;
		appDAO.deleteInstructorById(theId);
	}
	private void findInstructor(AppDAO appDAO){
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("Associated instructorDetail: "+tempInstructor.getInstructorDetail());
	}
	private void createInstructor(AppDAO appDAO){

		Instructor tempInstructor=new Instructor("Chad", "Darby" , "darby@luv2code.com");

		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube", "LUV 2 Code!!!");

		//associate the Objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//This will also save the details object because of CascadeType.ALL
		System.out.println("Saving instructor: "+ tempInstructor);
		appDAO.save(tempInstructor);

	}
}
