package com.training.crudjpademo.dao;

import java.util.List;

import com.training.crudjpademo.entity.Course;
import com.training.crudjpademo.entity.Instructor;
import com.training.crudjpademo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor instructor);
    void update(Instructor instructor);
    void update(Course course);

    Instructor findInstructorById(Integer id);
    InstructorDetail findIntstructorDetailById(Integer id);
    List<Course> findCoursesByInstructorById(Integer id);
    Instructor findInstructorByIdJoinFetchById(Integer id);
    Course findCourseById(Integer id);
    
    void deleteInstructorById(Integer id);
    void deleteInstructorDetailById(Integer id);
    void deleteCourseById(Integer id);
}
