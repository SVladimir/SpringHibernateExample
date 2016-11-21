package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.IProcessingImpl;
import com.rabbitmq.MessageProduser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ProcessingDoc {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);


       // System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());
        IProcessingImpl processing = new IProcessingImpl();
        MessageProduser messageProduser = new MessageProduser();
        List<Document> documents = documentDAO.list_by_status("NEW");
        for (Document document : documents) {


            messageProduser.sendMessage(document.getId().toString());

        }
            // processing.procces(document);


      //  System.out.println("FINISH PROCESSING TO DOC: " + document.getId());


        context.close();

    }

}
