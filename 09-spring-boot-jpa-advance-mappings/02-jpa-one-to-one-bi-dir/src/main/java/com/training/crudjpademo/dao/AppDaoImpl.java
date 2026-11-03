package com.training.crudjpademo.dao;

import org.springframework.stereotype.Repository;

import com.training.crudjpademo.entity.Instructor;
import com.training.crudjpademo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImpl implements AppDao {

    private final EntityManager _entityManager;

    public AppDaoImpl(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this._entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return this._entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer id) {
        Instructor instructorToDelete = this._entityManager.find(Instructor.class, id);

        this._entityManager.remove(instructorToDelete);
    }

    @Override
    public InstructorDetail findIntstructorDetailById(Integer id) {
        return this._entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional 
    public void deleteInstructorDetailById(Integer id) {
        InstructorDetail instructorDetail = this._entityManager.find(InstructorDetail.class, id);

        instructorDetail.getInstructor().setInstructorDetail(null);

        this._entityManager.remove(instructorDetail);
    }

}
