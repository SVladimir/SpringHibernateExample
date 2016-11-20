import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.ClientDAO;
import com.journaldev.model.Account;
import com.journaldev.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by vsshm_000 on 20.11.2016.
 */
public class TestAccount {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    ClientDAO clientDAO = context.getBean(ClientDAO.class);
    AccountDAO accountDAO=context.getBean(AccountDAO.class);
    Client  client= new Client("Romashka_test");
    Account account = new Account(client, "40817978912308495837");
    @Before
    public void Init()  throws Exception  {
        clientDAO.save(client);
        accountDAO.save(account);
    }
    @Test
    public void testFindClient() {
        Assert.assertTrue(account.getId() == account.find(client.getId()).getId());
    }
    @Test
    public void testDelClient() {
        clientDAO.removeClient(client.getId());
        Assert.assertNull(clientDAO.findClient(client.getId()));
    }



}
