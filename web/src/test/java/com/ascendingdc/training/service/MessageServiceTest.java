package com.ascendingdc.training.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.ascendingdc.training.init.AppBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS sqsClient;

    @Test
    public void sendMessageTest(){
        when(sqsClient.getQueueUrl(anyString())).thenReturn(mock(GetQueueUrlResult.class));
        messageService.sendMessage("ascending123",1);
        verify(sqsClient,times(1)).sendMessage(any(SendMessageRequest.class));
    }
}
