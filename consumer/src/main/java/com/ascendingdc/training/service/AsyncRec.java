package com.ascendingdc.training.service;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

@Service
public class AsyncRec {
    @Value("${aws.sqs.name}")
    private String queueName;

    @Autowired
    private AmazonSQS sqsClient;

    @Autowired
    private SQSConnectionFactory sqsConnectionFactory;

    public void asncReceive() throws JMSException, InterruptedException{
        //create a new connection factory
        /*SQSConnectionFactory connectionFactory=new SQSConnectionFactory(
                //new ProviderConfiguration(), AmazonSQSClientBuilder.defaultClient()
                //new ProviderConfiguration(), sqsClient   A!!!!!!!!!!!!!!!!!!!!!!!!!!
                new ProviderConfiguration(), AmazonSQSClientBuilder.standard().build()//why here are diff from AwsConfig
        );*/
        SQSConnection connection=sqsConnectionFactory.createConnection();
        //create the session
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //create a consumer
        MessageConsumer consumer=session.createConsumer(session.createQueue(queueName));
        // Instantiate and set the message listener for the consumer.
        consumer.setMessageListener(new MyListener());
        // Start receiving incoming messages.
        connection.start();
        // Wait for 1 second. The listener onMessage() method is invoked when a message is received
        Thread.sleep(1000);
    }
}
