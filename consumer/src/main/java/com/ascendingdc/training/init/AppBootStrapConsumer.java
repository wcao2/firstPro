package com.ascendingdc.training.init;

import com.ascendingdc.training.service.AsyncRec;
import com.ascendingdc.training.service.EmailUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.jms.JMSException;

@SpringBootApplication(scanBasePackages = {"com.ascendingdc.training"})
public class AppBootStrapConsumer {

    public static void main(String[] args) throws InterruptedException, JMSException {
        ConfigurableApplicationContext app = SpringApplication.run(AppBootStrapConsumer.class,args);
        AsyncRec ar=app.getBean(AsyncRec.class);
        ar.asncReceive();
        EmailUtil eu = app.getBean(EmailUtil.class);
        eu.sendMail("caowei20171218@gmail.com");
    }
}
