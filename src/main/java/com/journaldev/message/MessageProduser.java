package com.journaldev.message;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
/**
 * Created by vsshm_000 on 30.10.2016.
*/
public class MessageProduser {

    public static void sendMessage(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "myExchange";
        String routingKey = "testRoute";


            byte[] messageBodyBytes = (message).getBytes();
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);

        channel.close();
        conn.close();
    }
}