package com.training.cruddemo.dao;

import java.util.List;

import com.training.cruddemo.entity.Student;

public interface StudentDAO {

    public void save(Student student);

    public Student findById(Integer id);

    public List<Student> findAll();

    public List<Student> findByFirstName(String firstName);
    
    public void update(Student student);

    public void delete(Integer id);

    public int deleteAll();
}
