package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.entity.Account;
import java.util.*;
public interface AccountDAO {

    void addAccount();

    String getName();
    String getServiceCode();
    void setName(String name);
    void setServiceCode(String serviceCode);
    List<Account> findAccounts();

}
