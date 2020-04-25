package com.ascendingdc.training.init;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;


@Configuration
@Profile("dev")//运行spring项目 用的是这个正常的awsconfig
public class AWSConfig {

    //创建S3client 运行服务的时候自动运行这个方法 并且造就了这个instance 直接注入到s3 这样就可以用dependency injection
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3(){
        return   AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }
}
