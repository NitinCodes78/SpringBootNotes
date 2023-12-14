package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Makes it available for component scanning
@Repository
public class AccountDAOImpl implements AccountDAO{
    private String name;
    private String serviceCode;

    @Override
    public void addAccount() {
        System.out.println(getClass()+" is Adding account");
    }

    public String getName() {
        System.out.println(getClass()+" :getName() method");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+" :SetName() method");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+" :getServiceCode() method");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+" :setServiceCode() method");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> myAccounts=new ArrayList<>();

        Account ac1=new Account("John", "Silver");

        Account ac2=new Account("Mohan", "Bronze");

        Account ac3=new Account("Virat", "Gold");

        myAccounts.add(ac1);
        myAccounts.add(ac2);
        myAccounts.add(ac3);

        return myAccounts;
    }
}
