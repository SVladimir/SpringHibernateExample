/**
 * Created by vsshm_000 on 02.11.2016.
 */

import com.rabbitmq.MessageReceiver;
import org.junit.Assert;
import org.junit.Test;


public class TestRecevier {

    @Test
    public void ResievMes () {
        MessageReceiver messageReceiver = new MessageReceiver();
        try {

            System.out.printf("Будем проводить документ =" + messageReceiver.recciev());
        }  catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);

    }
}

