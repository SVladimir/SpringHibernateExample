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
	public void removeAll(){
		List<Client> clients = list();
		for (Client client : clients) {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(client);
			tx.commit();
			session.close();
		}
	}


}
