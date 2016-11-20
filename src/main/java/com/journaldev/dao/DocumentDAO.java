package com.journaldev.dao;

import com.journaldev.model.Document;

import java.util.List;

public interface DocumentDAO {

    public void save(Document d);

    public List<Document> list();

    public void removeDocument(Integer id);

    public Document findDocument(Integer id);

}
