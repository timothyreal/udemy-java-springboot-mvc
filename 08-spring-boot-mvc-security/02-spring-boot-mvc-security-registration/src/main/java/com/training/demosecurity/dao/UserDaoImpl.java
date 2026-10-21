package com.training.demosecurity.dao;

import org.springframework.stereotype.Repository;

import com.training.demosecurity.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository 
public class UserDaoImpl implements UserDao {

    private final EntityManager _entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {
        User user = null;
        TypedQuery<User> query = this._entityManager.createQuery("FROM User WHERE userName=:uName AND enabled=true", User.class);
        query.setParameter("uName", userName);

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        
        return user;
    }

    @Override
    public void save(User user) {
        this._entityManager.merge(user);
    }

}
