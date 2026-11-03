package com.training.crudjpademo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.crudjpademo.entity.Course;
import com.training.crudjpademo.entity.Instructor;
import com.training.crudjpademo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    @Transactional 
    public void save(Course course) {
        this._entityManager.persist(course);
    }

    @Override
    @Transactional 
    public void update(Instructor instructor) {
        this._entityManager.merge(instructor);
    }

    @Override
    @Transactional 
    public void update(Course course) {
        this._entityManager.merge(course);
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return this._entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetail findIntstructorDetailById(Integer id) {
        return this._entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorById(Integer id) {
        TypedQuery<Course> typedQuery = this._entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);
        typedQuery.setParameter("data", id);

        List<Course> courses = typedQuery.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetchById(Integer id) {
        TypedQuery<Instructor> typedQuery = this._entityManager.createQuery("SELECT i from Instructor i "
        + "JOIN FETCH i.courses "
        + "JOIN FETCH i.instructorDetail "
        + "WHERE i.id = :data", Instructor.class);

        typedQuery.setParameter("data", id);

        Instructor instructor = typedQuery.getSingleResult();
        return  instructor;
    }

    @Override
    public Course findCourseById(Integer id) {
        return this._entityManager.find(Course.class, id);
    }

    @Override
    public Course findCourseAndReviewsById(Integer id) {
        TypedQuery<Course> query = this._entityManager.createQuery(
            "SELECT c FROM Course c "
            + "JOIN FETCH c.reviews "
            + "WHERE c.id = :data",
        Course.class);

        query.setParameter("data", id);

        Course course = query.getSingleResult();
        return  course;
    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer id) {
        Instructor instructorToDelete = this._entityManager.find(Instructor.class, id);

        List<Course> courses = instructorToDelete.getCourses();
        for (Course course : courses) {
            course.setInstructor(null);
        }

        this._entityManager.remove(instructorToDelete);
    }

    @Override
    @Transactional 
    public void deleteInstructorDetailById(Integer id) {
        InstructorDetail instructorDetail = this._entityManager.find(InstructorDetail.class, id);

        instructorDetail.getInstructor().setInstructorDetail(null);

        this._entityManager.remove(instructorDetail);
    }

    @Override 
    @Transactional 
    public void deleteCourseById(Integer id) {
        Course course = this._entityManager.find(Course.class, id);

        this._entityManager.remove(course);
    }
}
