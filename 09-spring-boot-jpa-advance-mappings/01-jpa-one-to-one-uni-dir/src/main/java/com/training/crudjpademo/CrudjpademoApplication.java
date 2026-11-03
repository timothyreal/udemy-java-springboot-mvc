package com.training.crudjpademo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.crudjpademo.dao.AppDao;
import com.training.crudjpademo.entity.Instructor;
import com.training.crudjpademo.entity.InstructorDetail;

@SpringBootApplication
public class CrudjpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudjpademoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			// this.createInstructor(appDao);
			// this.findInstructorById(appDao);
			this.deleteInstructorById(appDao);
		};
	}

	void createInstructor(AppDao appDao) {
		// Instructor theInstructor = new Instructor(
		// 	"Dick", "Grayson", "grayson@test.com"
		// );

		// InstructorDetail theInstructorDetail = new InstructorDetail(
		// 	"https://www.youtube.com/",
		// 	"Guitar"
		// );

		// theInstructor.setInstructorDetail(theInstructorDetail);

		Instructor theInstructor = new Instructor(
			"Jason", "Todd", "todd@test.com"
		);

		InstructorDetail theInstructorDetail = new InstructorDetail(
			"https://www.youtube.com/",
			"Dying"
		);

		theInstructor.setInstructorDetail(theInstructorDetail);

		System.out.println("Saving instructor: " + theInstructor);
		appDao.save(theInstructor);

		System.out.println("Done saving!");
	}
	
	void findInstructorById(AppDao appDao) {
		int id = 2;
		System.out.println("Finding instructor id: " + id);

		Instructor theInstructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + theInstructor);
		System.out.println("Details: " + theInstructor.getInstructorDetail());
	}

	void deleteInstructorById(AppDao appDao) {
		int id = 2;
		System.out.println("Deleting instructor id: " + id);

		appDao.deleteInstructorById(id);
		System.out.println("Instructor deleted!");
	}
}
