package com.journaldev.dao;

import com.journaldev.model.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DocumentDAOImpl implements DocumentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Document p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Document> list() {
        Session session = this.sessionFactory.openSession();
        List<Document> DocumentList = session.createQuery("from Document").list();
        session.close();
        return DocumentList;
    }

    @Override
    public void removeDocument(Integer id) {
        Document document = (Document) sessionFactory.getCurrentSession().load(
                Document.class, id);
        if (null != document) {
            sessionFactory.getCurrentSession().delete(document);
        }

    }

    @Override
    public void removeAll() {
        List<Document> documents = list();
        for (Document document : documents) {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(document);
            tx.commit();
            session.close();
        }
    }


    @Override
    public Document findDocument(Long id) {

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Document document = (Document) session.load(
                Document.class, id);
        tx.commit();
        session.close();
        return document;

    }

}
