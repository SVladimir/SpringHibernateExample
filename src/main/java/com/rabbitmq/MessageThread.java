package com.rabbitmq;

import com.journaldev.dao.DocumentDAO;
import com.journaldev.model.Document;
import com.journaldev.service.ICancelImpl;
import com.journaldev.service.IProcessingImpl;
import com.rabbitmq.client.Channel;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vsshm_000 on 30.10.2016.
 */
public class MessageThread extends Thread {

    private Channel channel;
    private String queueName;
    private String message;
    private long tag;
    private  String type_op; //1 Проводка 0-Ликвидация
    public MessageThread(Channel channel, String message, long tag, String queueName) {
        this.channel = channel;
        this.message = message;
        this.tag = tag;
        this.queueName = queueName;

    }


    @Override
    public void run() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            DocumentDAO documentDAO = context.getBean(DocumentDAO.class);
            System.err.println("COMMING SOON PROCESSING TO DOC: " + message);
            type_op=message.substring(0,1);
            System.err.println("type_op: " + type_op);

            message=message.substring(1);
            System.err.println("message: " + message);
            Document document = documentDAO.findDocument(Long.parseLong(message));
            if (type_op.equals("0")) {
                ICancelImpl canseling = new ICancelImpl ();
                canseling.cancel(document);
            } else {
                IProcessingImpl processing = new IProcessingImpl();
                processing.procces(document);
            }





            //sleep(5000);

            channel.basicAck(tag, false);
            System.err.println("Message deleted " + message);
            context.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
