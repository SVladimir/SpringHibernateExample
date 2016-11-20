package com.journaldev.main;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.ClientDAO;
import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Account;
import com.journaldev.model.Client;
import com.journaldev.model.Document;
import com.journaldev.service.IProcessingImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class SpringHibernateMain {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		/*PersonDAO personDAO = context.getBean(PersonDAO.class);

		Person person = new Person();
		person.setName("Pankaj"); person.setCountry("India");

		personDAO.save(person);

		System.out.println("Person::"+person);*/

        ClientDAO clientDAO = context.getBean(ClientDAO.class);
        Client client = new Client("Romashka");
        clientDAO.save(client);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);
        Client bank = new Client("Sberbank");
        clientDAO.save(bank);
        Account account = new Account(client, "40817978912308495837");
        Account accountDep = new Account(client, "42305978391824839323");
        Account accountBank = new Account(bank, "20202810938493859685");
        accountDAO.save(account);
        accountDAO.save(accountDep);
        accountDAO.save(accountBank);


        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);

        Document document = new Document(accountBank, account, BigDecimal.valueOf(4700000, 2),
                "test1");
        Document documentDep = new Document(account, accountDep, BigDecimal.valueOf(4700000, 2),
                "test2");
        documentDAO.save(document);
        documentDAO.save(documentDep);
        System.out.println("Client::" + client.getName());
        System.out.println("Client bank::" + bank.getName());
        System.out.println("document: " + document.toString());
        System.out.println("documentDep: " + documentDep.toString());
        System.out.println("account: " + account.toString());
        System.out.println("accountDep: " + accountDep.toString());
        System.out.println("accountBank: " + accountBank.toString());

        System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());
        IProcessingImpl processing=new IProcessingImpl();
        processing.procces(document);
        System.out.println("FINISH PROCESSING TO DOC: " + document.getId());
        /*  System.out.println("COMMING SOON PROCESSING TO DOC: " + document.getId());
		List<Person> list = personDAO.list();
		
		for(Person p : list){
			System.out.println("Person List::"+p);
		}*/


        context.close();

    }

}
