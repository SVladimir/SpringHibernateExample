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
        System.out.print("accountDt saldo=" + accountDt.getSaldo());
        System.out.print("\t\t");
        System.out.print("accountCt saldo=" + accountCt.getSaldo());
        System.out.print("\t\t");
        System.out.print("doc sum=" + sum);
        System.out.print("\t\t");
        accountDt.setSaldo(accountDt.getSaldo().subtract(document.getSum()));
        accountCt.setSaldo(accountCt.getSaldo().add(document.getSum()));
        accountDAO.update(accountCt);
        accountDAO.update(accountDt);
        System.out.print("accountDt saldo=" + accountDt.getSaldo());
        System.out.print("\t\t");
        System.out.print("accountCt saldo=" + accountCt.getSaldo());
        System.out.print("\t\t");
    }
}
