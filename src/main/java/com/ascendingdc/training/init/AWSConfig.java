package com.ascendingdc.training.init;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//to show things in this class are bean
@Configuration
@Profile("dev")//运行spring项目 use normal的aws config
public class AWSConfig {

    //创建S3client 运行服务的时候自动运行这个方法 并且造就了这个instance 直接注入到Amazons3 这样就可以用dependency injection
    //establish S3 connection
    //@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    public AmazonS3 getAmazonS3(){
        return   AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    //AmazonSQS client Bean
    @Bean
    public AmazonSQS getAmazonSQS(){
        return AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(Regions.US_EAST_1).build();
    }
}
