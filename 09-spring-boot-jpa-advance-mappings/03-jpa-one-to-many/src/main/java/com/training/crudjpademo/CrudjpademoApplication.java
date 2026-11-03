package com.training.crudjpademo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.crudjpademo.dao.AppDao;
import com.training.crudjpademo.entity.Course;
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
			// this.createInstructorWithCourses(appDao);

			// this.findInstructorById(appDao, 1);
			// this.findIntructorWithCoursesById(appDao, 1);
			// this.findCoursesForInstructorById(appDao, 1);
			// this.findInstructorWithCoursesJoinFetchById(appDao, 1);
			// this.findInstructorDetailById(appDao, 1);

			// this.updateInstructorById(appDao, 1);
			// this.updateCourseById(appDao, 10);

			// this.deleteInstructorById(appDao,1);
			// this.deleteInstructorDetailById(appDao, 1);
			this.deleteCourseById(appDao, 10);
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
	
	void createInstructorWithCourses(AppDao appDao) {
		Instructor theInstructor = new Instructor(
			"Bruce", "Wayne", "wayne@test.com"
		);

		InstructorDetail theInstructorDetail = new InstructorDetail(
			"https://www.youtube.com/",
			"Vengance"
		);
		theInstructor.setInstructorDetail(theInstructorDetail);

		Course course1 = new Course("Driving");
		Course course2 = new Course("Punching");
		theInstructor.add(course1);
		theInstructor.add(course2);

		System.out.println("Saving instructor: " + theInstructor);
		System.out.println("Courses: " + theInstructor.getCourses());
		appDao.save(theInstructor);

		System.out.println("Done saving!");
	}
	
	void findInstructorById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor theInstructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + theInstructor);
		System.out.println("Details: " + theInstructor.getInstructorDetail());
	}

	void findInstructorDetailById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		InstructorDetail theInstructorDetail = appDao.findIntstructorDetailById(id);

		System.out.println("Details: " + theInstructorDetail);
		System.out.println("Instructor: " + theInstructorDetail.getInstructor());
	}

	void findIntructorWithCoursesById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		// Will throw error with FetchType.LAZY
		System.out.println("Courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	void findCoursesForInstructorById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDao.findInstructorById(id);

		System.out.println("Instructor: " + instructor);

		System.out.println("Find courses for instroctor id: " + id);
		List<Course> courses = appDao.findCoursesByInstructorById(id);
		instructor.setCourses(courses);

		System.out.println("Courses: " + instructor.getCourses());
		System.out.println("Done");
	}

	void findInstructorWithCoursesJoinFetchById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDao.findInstructorByIdJoinFetchById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());

		System.out.println("Done");
	}

	void updateInstructorById(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDao.findInstructorById(id);

		System.out.println("Updating instructor id: " + id);
		instructor.setFirstName("Thomas");
		
		appDao.update(instructor);
		System.out.println("Done!");
	}

	void updateCourseById(AppDao appDao, int id) {
		System.out.println("Finding Course id: " + id);

		Course course = appDao.findCourseById(id);

		System.out.println("Updating course id: " + id);
		course.setTitle("Business");
		
		appDao.update(course);
		System.out.println("Done!");
	}

	void deleteInstructorDetailById(AppDao appDao, int id) {
		System.out.println("Deleting instructor details by id: " + id);

		appDao.deleteInstructorDetailById(id);

		System.out.println("Instrucor details deleted!");
	}

	void deleteInstructorById(AppDao appDao, int id) {
		System.out.println("Deleting instructor id: " + id);

		appDao.deleteInstructorById(id);
		System.out.println("Instructor deleted!");
	}

	void deleteCourseById(AppDao appDao, int id) {
		System.out.println("Delete course id: " + id);
		
		appDao.deleteCourseById(id);

		System.out.println("Course Deleted!");
	}
}
