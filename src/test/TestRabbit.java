/**
 * Created by vsshm_000 on 02.11.2016.
 */


import com.rabbitmq.MessageProduser;
import com.rabbitmq.MessageReceiver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRabbit {
    @Before
    public void Init()  throws Exception  {
        MessageProduser messageProduser = new MessageProduser();

            messageProduser.sendMessage("Test messge");

    }
    @Test
    public void testResievMes () throws Exception{
        MessageReceiver messageReceiver=new MessageReceiver();

      Assert.assertTrue(messageReceiver.recciev().equals("Test messge"));
    }
}
