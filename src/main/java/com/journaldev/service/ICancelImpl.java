package com.journaldev.service;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Account;
import com.journaldev.model.Document;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * Created by vsshm_000 on 20.11.2016.
 */
/*Отмена проводки документа*/
public class ICancelImpl implements ICancel {
    @Override
    public void cancel(Document document) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DocumentDAO documentDAO = context.getBean(DocumentDAO.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);
        Account accountDt = null;
        Account accountCt = null;
        BigDecimal sum = BigDecimal.valueOf(0);
        sum = document.getSum();
        accountDt = document.getAccountDt();
        accountCt = document.getAccountCt();
        System.out.println("accountDt saldo=" + accountDt.getSaldo());
        System.out.println("accountCt saldo=" + accountCt.getSaldo());
        System.out.println("doc sum=" + sum);
        accountCt.setSaldo(accountCt.getSaldo().subtract(document.getSum()));
        accountDt.setSaldo(accountDt.getSaldo().add(document.getSum()));
        accountDAO.update(accountCt);
        accountDAO.update(accountDt);
        document.setStatus("NEW");
        documentDAO.update(document);
        System.out.println("accountDt saldo=" + accountDt.getSaldo());
        System.out.println("accountCt saldo=" + accountCt.getSaldo());
        System.out.println("document Status=" + document.getStatus());


    }

}
