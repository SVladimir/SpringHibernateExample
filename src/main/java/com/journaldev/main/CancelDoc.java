package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.ICancelImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CancelDoc {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);


        ICancelImpl cancel = new ICancelImpl();
        List<Document> documents = documentDAO.list_by_status("PROCESSED");
        for (Document document : documents) {
            System.out.println("COMMING SOON CANSELED TO DOC: " + document.getId());
            cancel.cancel(document);
            System.out.println("FINISH CANSELED TO DOC: " + document.getId());

        }


        context.close();

    }

}
