package com.ascendingdc.training.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.*;

@Configuration
@Profile("unit")
public class AWSConfigTest {
    @Bean
    //when do unit test, Inject a hypothetical bean
    public AmazonS3 getAmazonS3(){
        return mock(AmazonS3.class);
    }

    @Bean
    public AmazonSQS getAmazonSQS(){
        return mock(AmazonSQS.class);
    }
}













