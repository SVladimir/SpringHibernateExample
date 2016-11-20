package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.IProcessingImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateMain {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);

        Document document = documentDAO.findDocument(7L);
        System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());
        IProcessingImpl processing = new IProcessingImpl();
        processing.procces(document);
        System.out.println("FINISH PROCESSING TO DOC: " + document.getId());


        context.close();

    }

}
