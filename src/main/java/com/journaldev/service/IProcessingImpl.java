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
public class IProcessingImpl implements IProccesing {
    @Override
    public void procces(Document document) {
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
        /*Проверка остатка по счету*/
        if (accountDt.getSaldo().subtract(sum).compareTo(BigDecimal.valueOf(0))!=-1) {
            accountDt.setSaldo(accountDt.getSaldo().subtract(document.getSum()));
            accountCt.setSaldo(accountCt.getSaldo().add(document.getSum()));
            accountDAO.update(accountCt);
            accountDAO.update(accountDt);
            document.setStatus("PROCESSED");
            documentDAO.update(document);
            System.out.println("accountDt saldo=" + accountDt.getSaldo());
            System.out.println("accountCt saldo=" + accountCt.getSaldo());
            System.out.println("document Status=" + document.getStatus());
        }else {  System.out.println("accountDt  Недостаточно средст=" + accountCt.getSaldo());}

    }
}
