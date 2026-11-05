package com.training.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.aopdemo.Account;

@Repository 
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    private List<Account> accounts;
    
    public String getName() {
        System.out.println(getClass() + ": getName()\n");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()\n");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()\n");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()\n");
        this.serviceCode = serviceCode;
    }

    @Override 
    public void addAccount(Account account, boolean vip) {
        System.out.println(getClass() + ": DB: Adding an account\n");

        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }

        this.accounts.add(account);
    }

    @Override
    public List<Account> findAccounts() {
        return  this.accounts;
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()\n");
        return false;
    }
}
