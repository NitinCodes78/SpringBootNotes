package com.luv2code.springcoredemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//This annotation in the background enables @EnableAutoConfiguration, @ComponentScan and @Configuration
@SpringBootApplication
public class SpringcoredemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}
}
