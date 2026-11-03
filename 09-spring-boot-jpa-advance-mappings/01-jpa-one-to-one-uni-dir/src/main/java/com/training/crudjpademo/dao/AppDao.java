package com.training.crudjpademo.dao;

import com.training.crudjpademo.entity.Instructor;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(Integer id);
    void deleteInstructorById(Integer id);
}
