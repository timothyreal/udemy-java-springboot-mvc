package com.training.aopdemo.dao;

import java.util.List;

import com.training.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vip);

    List<Account> findAccounts();
    List<Account> findAccounts(boolean forceException);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
