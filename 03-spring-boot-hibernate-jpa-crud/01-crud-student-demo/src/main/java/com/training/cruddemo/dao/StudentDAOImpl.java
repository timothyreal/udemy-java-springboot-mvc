package com.training.cruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager _entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        this._entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return this._entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = this._entityManager.createQuery("FROM Student ORDER BY firstName ASC", Student.class);

        return query.getResultList();
    }

    @Override 
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> query = this._entityManager.createQuery("FROM Student WHERE firstName=:firstName", Student.class);
        query.setParameter("firstName", firstName);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        this._entityManager.merge(student);
    }

    @Override
    @Transactional 
    public void delete(Integer id) {
        Student student = this._entityManager.find(Student.class, id);

        this._entityManager.remove(student);
    }

    @Override 
    @Transactional 
    public int deleteAll() {
        int numsRowDeleted = this._entityManager.createQuery("DELETE FROM Student").executeUpdate();
        
        return numsRowDeleted;
    }
}
