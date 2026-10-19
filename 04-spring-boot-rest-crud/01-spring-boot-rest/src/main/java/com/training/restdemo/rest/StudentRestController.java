package com.training.restdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.restdemo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private List<Student> _students;

    @PostConstruct
    public void loadData() {
        this._students = new ArrayList<>();

        this._students.add(new Student("Juan", "Dela Cruz"));
        this._students.add(new Student("Maria", "Clara"));
        this._students.add(new Student("Jose", "Rizal"));
    }

    @GetMapping
    public List<Student> getStudents() {
        return this._students;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable(name="studentId") int theStudentId) {
        if (theStudentId >= this._students.size() || theStudentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + theStudentId);
        }
        return this._students.get(theStudentId);
    }
}
