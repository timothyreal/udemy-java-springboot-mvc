package com.training.demosecurity.dao;

import com.training.demosecurity.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    void save(User user);
}
