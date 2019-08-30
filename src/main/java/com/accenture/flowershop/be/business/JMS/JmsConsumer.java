package com.accenture.flowershop.be.business.JMS;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Service
public class JmsConsumer implements AutoCloseable
{

    private QueueConnection queueConnection = null;

    public JmsConsumer(){

    }

    @PostConstruct
    public void init() throws JMSException, NamingException {
        System.out.println("Init consumer...");
        InitialContext initCtx = new InitialContext();
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initCtx
                .lookup("java:comp/env/jms/connectionFactory");

        System.out.println("Создание приемника...");

        this.queueConnection = connectionFactory.createQueueConnection();
        this.queueConnection.start();
        System.out.println("Приемник успешно создан");
    }


    public Message receiveMSG(long ms) throws JMSException, NamingException {
           InitialContext initCtx = new InitialContext();
           QueueSession queueSession =  this.queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
           Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/outQueue");
           QueueReceiver queueReceiver = queueSession.createReceiver(queue);
           Message msg = queueReceiver.receive(ms);

        try
        {
            if (queueSession != null)
                queueSession.close();
        }
        catch (JMSException jmsEx)
        {
            jmsEx.printStackTrace();
        }
           return  msg;
    }


    @PreDestroy
    public void close() throws Exception {
        try {
            if (queueConnection != null)
                queueConnection.close();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
