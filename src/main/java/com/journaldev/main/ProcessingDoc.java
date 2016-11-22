package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.rabbitmq.MessageProduser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
/*Метод обработки документов
* Обрабатывает все документы в статусе NEW*/
public class ProcessingDoc {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);


       // System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());

        MessageProduser messageProduser = new MessageProduser();
        List<Document> documents = documentDAO.list_by_status("NEW");
        for (Document document : documents) {


            messageProduser.sendMessage("1"+document.getId().toString());

        }
            // processing.procces(document);


      //  System.out.println("FINISH PROCESSING TO DOC: " + document.getId());


        context.close();

    }

}
