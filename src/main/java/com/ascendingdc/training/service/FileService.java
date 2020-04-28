package com.ascendingdc.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    //private String defaultRegion="us-east-1";
    private String bucketName="ascending-weicao";
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonS3 s3Client;
    //用mock不会去真正调用第三方Amazon的API

    //upload an Object using the AWS SDK for Java
    public void uploadFile(File file){
        //1.1 1.2
        //AmazonS3 s3Client=AmazonS3ClientBuilder.standard().withRegion(defaultRegion).build();

        //1.1 upload a file with specified content
        //s3Client.putObject("ascending-weicao","sampleFile.txt","this is first upload String file");

        //1.2 Upload a file as a new object with ContentType and title specified.
        /*PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
        s3Client.putObject(request);*/

        //1.2+
        if(file!=null){
            PutObjectRequest req=new PutObjectRequest(bucketName,file.getName(),file);
            System.out.println(file.getName());
            s3Client.putObject(req);
        }else{
            logger.error("can not upload the file");
        }
    }

    //
    public String getUrl(String s3Key){
        return s3Client.getUrl(bucketName,s3Key).toString();
    }
}
