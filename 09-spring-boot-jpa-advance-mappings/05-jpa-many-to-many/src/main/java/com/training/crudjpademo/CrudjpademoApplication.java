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
import com.training.crudjpademo.entity.Review;
import com.training.crudjpademo.entity.Student;

@SpringBootApplication
public class CrudjpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudjpademoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			// this.createCourseAndStudents(appDao);
			// this.findCourseAndStudentByCourseId(appDao, 10);
			// this.findStudentAndCourseByStudentId(appDao, 1);
			// this.updateStudentCoursesByStudentId(appDao, 1);
			// this.deleteCourseById(appDao, 10);
			this.deleteStudentById(appDao, 2);
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
	
	void createCourseAndReviews(AppDao appDao) {
		Course course = new Course("Programming");

		course.addReview(new Review("Amazing!"));
		course.addReview(new Review("I love it!"));
		course.addReview(new Review("Cool!"));

		System.out.println("Saving course with reviews!");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDao.save(course);

		System.out.println("Course Saved!");
	}

	void createCourseAndStudents(AppDao appDao) {
		Course course = new Course("Programming");

		Student student1 = new Student("Jose", "Rizal", "jose.rizal@test.com");
		Student student2 = new Student("Andres", "Bonifacio", "andres.bonifacio@test.com");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Saving course: " + course);
		System.out.println("Students: " + course.getStudents());

		appDao.save(course);
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

	void findInstructorWithCoursesJoinFetchByID(AppDao appDao, int id) {
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDao.findInstructorByIdJoinFetchById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());

		System.out.println("Done");
	}

	void findCourseAndReviewsById(AppDao appDao, int id) {
		Course course = appDao.findCourseAndReviewsById(id);

		System.out.println(course);
		System.out.println(course.getReviews());
	}

	void findCourseAndStudentByCourseId(AppDao appDao, int id) {
		Course course = appDao.findCourseAndStudentByCourseId(id);

		System.out.println(course);
		System.out.println(course.getStudents());
	}

	void findStudentAndCourseByStudentId(AppDao appDao, int id) {
		Student student = appDao.findStudentAndCourseByStudentId(id);

		System.out.println(student);
		System.out.println(student.getCourses());
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

	void updateStudentCoursesByStudentId(AppDao appDao, int id) {
		Student student = appDao.findStudentAndCourseByStudentId(id);

		Course course1 = new Course("Writing");
		Course course2 = new Course("Fencing");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student: " + student);
		System.out.println("Courses" + student.getCourses());

		appDao.update(student);

		System.out.println("Course updated!");
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

	void deleteStudentById(AppDao appDao, int id) {
		System.out.println("Delete student id: " + id);
		
		appDao.deleteStudentById(id);

		System.out.println("Student Deleted!");
	}
}
