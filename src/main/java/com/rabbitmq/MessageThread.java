package com.rabbitmq;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.IProcessingImpl;
import com.rabbitmq.client.Channel;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            DocumentDAO documentDAO = context.getBean(DocumentDAO.class);
            System.out.println("COMMING SOON PROCESSING TO DOC: " + message);
            IProcessingImpl processing = new IProcessingImpl();
            Document document = documentDAO.findDocument(Long.parseLong(message));
            processing.procces(document);


             System.out.println("FINISH PROCESSING TO DOC: " + document.getId());



            sleep(5000);

            channel.basicAck(tag, false);
            System.err.println("Message deleted " + message);
            context.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
