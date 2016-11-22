package com.journaldev.dao;

import com.journaldev.model.Client;
import com.journaldev.model.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Client p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> list() {
        Session session = this.sessionFactory.openSession();
        List<Client> ClientList = session.createQuery("from Client").list();
        session.close();
        return ClientList;
    }

    @Override
    public Client findByName(String name) {
        Session session = this.sessionFactory.openSession();
        List<Client> ClientList = session.createQuery("from Client d where d.name = :name").setParameter("name", name).list();
        session.close();
        for (Client client : ClientList) {
            return client;
        }
        /*Not found*/
        return null;
    }

    @Override
    public Client findClient(Long id) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Client client = (Client) session.get(
                Client.class, id);
        tx.commit();
        session.close();
        return client;

    }

    @Override
    public void removeAll() {
        List<Client> clients = list();
        for (Client client : clients) {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(client);
            tx.commit();
            session.close();
        }
    }

    @Override
    public void removeClient(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Client client = (Client) session.get(
                Client.class, id);
        if (null != client) {
            session.delete(client);
        }
        tx.commit();
        session.close();

    }

}
