package com.training.crudjpademo.dao;

import com.training.crudjpademo.entity.Instructor;
import com.training.crudjpademo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(Integer id);
    void deleteInstructorById(Integer id);
    
    InstructorDetail findIntstructorDetailById(Integer id);
    void deleteInstructorDetailById(Integer id);
}
