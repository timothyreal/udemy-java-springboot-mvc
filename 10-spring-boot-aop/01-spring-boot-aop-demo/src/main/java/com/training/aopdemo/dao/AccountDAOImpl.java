package com.training.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.training.aopdemo.Account;

@Repository 
public class AccountDAOImpl implements AccountDAO {

    @Override 
    public void addAccount(Account account, boolean vip) {
        System.out.println(getClass() + ": DB: Adding an account\n");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()\n");
        return false;
    }
}
