package com.journaldev.main;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.ICancelImpl;
import com.rabbitmq.MessageProduser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CancelDoc {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);


        ICancelImpl cancel = new ICancelImpl();
        MessageProduser messageProduser = new MessageProduser();
        List<Document> documents = documentDAO.list_by_status("PROCESSED");
        for (Document document : documents) {
            messageProduser.sendMessage("0"+document.getId().toString());

        }


        context.close();

    }

}
