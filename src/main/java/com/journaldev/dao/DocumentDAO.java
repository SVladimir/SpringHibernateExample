package com.journaldev.dao;

import com.journaldev.model.Document;

import java.util.List;

public interface DocumentDAO {

    public void save(Document d);

    public List<Document> list();


    List<Document> list_by_status(String status);

    public void removeDocument(Integer id);

    public Document findDocument(Long id);

    public void removeAll();

    void update(Document d);
}
