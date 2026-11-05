package com.training.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository 
public class MembershipDAOImpl implements MembershipDAO {

    @Override 
    public void addMember() {
        System.out.println(getClass() + ": DB: Adding membership\n");
    }

    @Override
    public boolean addSomething() {
        System.out.println(getClass() + ": DB: Add something\n");
        return false;
    }
}
