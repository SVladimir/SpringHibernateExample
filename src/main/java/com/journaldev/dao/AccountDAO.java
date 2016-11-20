package com.journaldev.dao;

import com.journaldev.model.Account;

import java.util.List;

public interface AccountDAO {

    public void save(Account p);

    public List<Account> list();

    public void update(Account a);

    void removeAccount(Long id);

    Account findAcc(Long id);

    public void removeAll();

}
