package com.journaldev.main;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.ClientDAO;
import com.journaldev.dao.DocumentDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EraseDate {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);
        documentDAO.removeAll();
        System.out.println("Kill all documents: !!!!!!!!!!!!!");
        AccountDAO accountDAO = context.getBean(AccountDAO.class);
        accountDAO.removeAll();
        System.out.println("Kill all accounts: !!!!!!!!!!!!!");
        ClientDAO clientDAO = context.getBean(ClientDAO.class);
        clientDAO.removeAll();
        System.out.println("Kill all clients: !!!!!!!!!!!!!");
        context.close();

    }

}
