package com.mengka.springboot.jpa;

import com.mengka.springboot.jpa.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mengka.springboot.jpa.repository.InstructorRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public void run(String... args) throws Exception {

		Instructor instructor = new Instructor("Ramesh", "Fadatare", "ramesh@gmail.com");

//		InstructorDetail instructorDetail = new InstructorDetail("Java Guides", "Cricket Playing");
//		instructorDetail.setId(100L);
//		// associate the objects
//		instructor.setInstructorDetail(instructorDetail);

		instructorRepository.save(instructor);
	}
}
