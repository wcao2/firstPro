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
    private String defaultRegion="us-east-2";
    private String bucketName="ascending-wei";
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonS3 s3Client;
    //用mock不会去真正调用第三方Amazon的API

    public void uploadFile(File f){//1:54
        //s3Client.putObject("ascending-wei","sampleFile.txt","this is first upload String file");
        // Upload a file as a new object with ContentType and title specified.
//        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
//        s3Client.putObject(request);
        //s3Client.putObject("ascending-wei","sampleFile.txt","helllo111 from here");
        if(f!=null){
            PutObjectRequest req=new PutObjectRequest(bucketName,f.getName(),f);
            //System.out.println(f.getName());
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
