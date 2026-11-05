package com.training.aopdemo.dao;

import com.training.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vip);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
