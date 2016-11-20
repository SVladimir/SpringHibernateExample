package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.IProcessingImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ProcessingDoc {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);


       // System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());
        IProcessingImpl processing = new IProcessingImpl();
        List<Document> documents = documentDAO.list_by_status("NEW");
        for (Document document : documents) {
            processing.procces(document);
        }

      //  System.out.println("FINISH PROCESSING TO DOC: " + document.getId());


        context.close();

    }

}
