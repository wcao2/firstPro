package com.ascendingdc.training.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailUtil {

    @Value("${gmail.address}")
    private String email;

    @Value("${gmail.password}")
    private String password;

    public void sendMail(String recepientEmail){
        //properties is key-value store
        Properties properties=new Properties();
        //To send a mail, have to configure minimum four fields(Transport Layer Security)
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String consumerEmail="caowei20171218@gmail.com";
        String consumerPassword="xian1992";

        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(consumerEmail,consumerPassword);
            }
        });
        //create a new message
        Message msg=new MimeMessage(session);

        //set the from and to fields
        try {
            msg.setFrom(new InternetAddress(consumerEmail));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recepientEmail));
            msg.setSubject("SQS CONFIRMED");
            msg.setText("Successfully upload the file to S3");
            msg.setSentDate(new Date());
            String htmlCode="<h3>This message comes from AWS SQS CONFIRMED</h3><br/><h3 style=\"color:Tomato;\">Successfully upload the file to S3</h3>";
            msg.setContent(htmlCode ,"text/html");
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Email send successfully");
    }

    public static void main(String[] args) {
        EmailUtil eu=new EmailUtil();
        eu.sendMail("caowei20171218@gmail.com");
    }
}
