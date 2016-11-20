package com.journaldev.message;

import com.rabbitmq.client.Channel;

/**
 * Created by vsshm_000 on 30.10.2016.
 */
public class MessageThread extends Thread {

    private Channel channel;
    private String message;
    private long tag;

    public MessageThread(Channel channel, String message, long tag) {
        this.channel = channel;
        this.message = message;
        this.tag = tag;
    }

    @Override
    public void run() {
        try {
            System.err.println("Message received " + message);
            sleep(5000);
            channel.basicAck(tag, false);
            System.err.println("Message deleted " + message);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
