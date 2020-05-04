package com.ascendingdc.training.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    //private String queueUrl="https://sqs.us-east-1.amazonaws.com/779483623482/ascending-queue-weicao";

    @Value("${aws.sqs.name}")
    private String queueName;

    @Autowired
    private AmazonSQS sqsClient;

    //send a message to sqs
    /*public void sendMessage(String messageBody, int delaySec){
        SendMessageRequest send_msg_request=new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(messageBody).withDelaySeconds(delaySec);
        sqs.sendMessage(send_msg_request);
    }*/
    public void sendMessage(String messageBody,int delaySec){
        SendMessageRequest sendMsgRequest=new SendMessageRequest()
                .withQueueUrl(getQueUrl(queueName))
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySec);
        sqsClient.sendMessage(sendMsgRequest);
    }

    private String getQueUrl(String name){
        GetQueueUrlResult getQueueUrlResult=sqsClient.getQueueUrl(name);
        return getQueueUrlResult.getQueueUrl();
    }
}






















