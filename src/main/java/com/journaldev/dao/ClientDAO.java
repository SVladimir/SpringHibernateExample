package com.journaldev.dao;

import com.journaldev.model.Client;

import java.util.List;

public interface ClientDAO {

    public void save(Client c);

    public List<Client> list();

    Client findClient(Long id);

    public void removeAll();

    void removeClient(Long id);
}
