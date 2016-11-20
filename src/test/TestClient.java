import com.journaldev.dao.ClientDAO;
import com.journaldev.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Created by vsshm_000 on 20.11.2016.
 */
public class TestClient {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    ClientDAO clientDAO = context.getBean(ClientDAO.class);
    Client  client= new Client("Romashka_test");

    @Before
    public void Init()  throws Exception  {
        clientDAO.save(client);
    }
    @Test
    public void testFindClient() {
        Assert.assertTrue(client.getId() == clientDAO.findClient(client.getId()).getId());
    }
    @Test
    public void testDelClient() {
        clientDAO.removeClient(client.getId());
        Assert.assertNull(clientDAO.findClient(client.getId()));
    }

    /**
     * Created by vsshm_000 on 20.11.2016.
     */
    public static class TestAccount {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ClientDAO clientDAO = context.getBean(ClientDAO.class);
        Client  client= new Client("Romashka_test");

        @Before
        public void Init()  throws Exception  {
            clientDAO.save(client);
        }
        @Test
        public void testFindClient() {
            Assert.assertTrue(client.getId() == clientDAO.findClient(client.getId()).getId());
        }
        @Test
        public void testDelClient() {
            clientDAO.removeClient(client.getId());
            Assert.assertNull(clientDAO.findClient(client.getId()));
        }

       }
}
