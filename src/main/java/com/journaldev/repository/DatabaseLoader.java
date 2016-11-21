package com.journaldev.repository;
/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.journaldev.model.Account;
import com.journaldev.model.Client;
import com.journaldev.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
@ComponentScan("com.journaldev.model")
public class DatabaseLoader implements CommandLineRunner {

    private final DocumentRepository repositoryDocument;
    private final ClientRepository repositoryClient;
    private final AccountRepository repositoryAccount;
    //private final DocumentRepository repositoryDocument;



    @Autowired
    public DatabaseLoader(DocumentRepository repositoryDocument, ClientRepository repositoryClient, AccountRepository repositoryAccount) {
        this.repositoryDocument = repositoryDocument;
        this.repositoryClient = repositoryClient;
        this.repositoryAccount = repositoryAccount;
    }

    @Override
    public void run(String... strings) throws Exception {
        String clientName = "Ivanov Petr";
        Client client = new Client(clientName);
        this.repositoryClient.save(client);
        Account account = new Account(client, "40817978912345678910");
        this.repositoryAccount.save(account);
        Account accountDep = new Account(client, "42305978912345678923");
        this.repositoryAccount.save(accountDep);
        System.out.printf("client id"+client.getId());
        Client bank = new Client("Sberbank");
        this.repositoryClient.save(bank);
        Account accountBank = new Account(bank, "30301978391344839323");
        this.repositoryAccount.save(accountBank);
        this.repositoryDocument.save(new Document(accountBank, account, BigDecimal.valueOf(4700000, 2),
                "test1"));

    }
}
// end::code[]