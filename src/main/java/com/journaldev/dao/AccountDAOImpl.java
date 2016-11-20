package com.journaldev.dao;

import com.journaldev.model.Account;
import com.journaldev.model.Client;
import com.journaldev.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Account a) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(a);
		tx.commit();
		session.close();
	}
	@Override
	public void update(Account a) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(a);
		tx.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> list() {
		Session session = this.sessionFactory.openSession();
		List<Account> accountList = session.createQuery("from Account").list();
		session.close();
		return accountList;
	}

	@Override
	public void removeAccount(Long id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Account account = (Account) session.get(
				Account.class, id);
		if (null != account) {
			session.delete(account);
		}
		tx.commit();
		session.close();

	}
	@Override
	public Account findAcc(Long id) {

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Account account = (Account) session.get(
				Account.class, id);
		tx.commit();
		session.close();
		return account;

	}
	@Override
	public void removeAll(){
		List<Account> accounts = list();
		for (Account account : accounts) {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(account);
			tx.commit();
			session.close();
		}

}}
