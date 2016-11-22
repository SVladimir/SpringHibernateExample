package com.journaldev.main;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.ClientDAO;
import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Account;
import com.journaldev.model.Client;
import com.journaldev.model.Document;
import com.rabbitmq.MessageProduser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Random;
/*Тестовый метод эмулирующий создание потока документов для обработки*/
public class ExampleWork {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ClientDAO clientDAO = context.getBean(ClientDAO.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);
        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);
        /*Поиск реквизитов банка*/
        Client bank = clientDAO.findByName("Sberbank");
        Account accountBank = accountDAO.findByNum("20202810938493859685");
        MessageProduser messageProduser = new MessageProduser();
        /*Создаем тестовые документы*/
        for  (int x = 0; x < 100; x++)
        {  /*Создаем тестового контрагента*/
            Client clientForTest = new Client("TestClient"+x);
            clientDAO.save(clientForTest);
             /*Создаем тестовый счет для контрагента*/
            int snum= new Random().ints(1000000000,2000000000).findFirst().getAsInt();

            System.out.println("snum: " +  "4081797891"+ snum);
            Account account = new Account(clientForTest, "4081797891"+snum);
            account.setSaldo(BigDecimal.valueOf(1000, 2));
            accountDAO.save(account);
            Document document = new Document(accountBank, account, BigDecimal.valueOf(x, 2),
                    "test"+x);
            documentDAO.save(document);
            messageProduser.sendMessage("1"+document.getId().toString());
            System.out.println("document: " + document.toString());

        }



        context.close();

    }

}
