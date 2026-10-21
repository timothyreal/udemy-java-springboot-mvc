package com.training.demosecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.training.demosecurity.entity.User;
import com.training.demosecurity.user.WebUser;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);

    public void save (WebUser webUser);

}
