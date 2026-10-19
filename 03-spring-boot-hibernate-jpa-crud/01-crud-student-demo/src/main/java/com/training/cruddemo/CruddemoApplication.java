package com.training.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.cruddemo.dao.StudentDAO;
import com.training.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			// this.createStudent(studentDAO);

			this.createMultipleStudents(studentDAO);

			// this.readStudent(studentDAO);

			// this.queryForStudents(studentDAO);

			// this.queryForStudentsByLastName(studentDAO);

			// this.updateStudent(studentDAO);

			// this.deleteStudent(studentDAO);

			// this.deleteAllStudents(studentDAO);
		};
	}

	// private void createStudent(StudentDAO studentDAO) {
	// 	System.out.println("Creating new student object ...");
	// 	Student tempStudent = new Student("Mario", "Brothers", "mario.brothers@example.com");

	// 	System.out.println("Saving the student ...");
	// 	studentDAO.save(tempStudent);
		
	// 	System.out.println("Saved student. Generated id: " + tempStudent.getId());
	// }

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student objects ...");
		Student tempStudent1 = new Student("Wario", "Brothers", "wario.brothers@example.com");
		Student tempStudent2 = new Student("Luigi", "Mario", "luigi.mario@example.com");
		Student tempStudent3 = new Student("Daisy", "Johnson", "daisy.johnson@example.com");

		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	// private void readStudent(StudentDAO studentDAO) {
	// 	System.out.println("Creating new student object ...");
	// 	Student tempStudent = new Student("Mickey", "Mouse", "mickey.mouse@example.com");

	// 	System.out.println("Saving the student ...");
	// 	studentDAO.save(tempStudent);
		
	// 	int id = tempStudent.getId();
	// 	System.out.println("Saved student. Generated id: " + id);

	// 	Student studentFound = studentDAO.findById(id);
	// 	System.out.println("Found the student: " + studentFound);
	// }

	// private void queryForStudents(StudentDAO studentDAO) {
	// 	List<Student> students = studentDAO.findAll();

	// 	for (Student student : students) {
	// 		System.out.println(student);
	// 	}
	// }

	// private void queryForStudentsByLastName(StudentDAO studentDAO) {
	// 	List<Student> students = studentDAO.findByFirstName("Mario");

	// 	for (Student student : students) {
	// 		System.out.println(student);
	// 	}
	// }

	// private void updateStudent(StudentDAO studentDAO) {
	// 	int studentId = 2;
	// 	System.out.println("Getting student with id: " + studentId);
	// 	Student student = studentDAO.findById(studentId);

	// 	System.out.println("Updating student ...");
	// 	student.setFirstName("Waluigi");
	// 	studentDAO.update(student);

	// 	System.out.println("Updated student: " + student);
	// }

	// public void deleteStudent(StudentDAO studentDAO) {
	// 	int studentId = 5;
	// 	System.out.println("Deleting student with id: " + studentId);
	// 	studentDAO.delete(studentId);
	// }

	// public void deleteAllStudents(StudentDAO studentDAO) {
	// 	System.out.println("Delete all studens");

	// 	int numRowsDeleted = studentDAO.deleteAll();

	// 	System.out.println("Num of rows deleted " + numRowsDeleted);


	// }
}
