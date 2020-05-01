package com.ascendingdc.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import java.net.URL;
import java.util.UUID;

@Service
public class FileService {
    //private String defaultRegion="us-east-1";

    @Value("${aws.s3.bucketName}")//through vm option
    private String bucketName;
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonS3 s3Client;
    //upload an Object using the AWS SDK for Java
    public void uploadFile(File file){
        //1
        //AmazonS3 s3Client=AmazonS3ClientBuilder.standard().withRegion(defaultRegion).build();
        //upload a file with specified content
        //s3Client.putObject("ascending-weicao","sampleFile.txt","this is first upload String file");

        //2
        if(file!=null){
            PutObjectRequest req=new PutObjectRequest(bucketName,file.getName(),file);
            System.out.println(file.getName().toString());//with out stub,there will be null pointer exception
            s3Client.putObject(req);
        }else{
            logger.error("can not upload the file");
        }
    }

    //real use
    public String uploadFile(String bucketName, MultipartFile file) {
        String uuid= UUID.randomUUID().toString();
        String originalFileName=file.getOriginalFilename();
        String newFileName= uuid+"."+ Files.getFileExtension(originalFileName);
        ObjectMetadata objectMetadata=new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try {
            s3Client.putObject(bucketName,newFileName,file.getInputStream(),objectMetadata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFileName;
    }

    public String getUrl(String bucketName,String s3Key){
        //return s3Client.getUrl(bucketName,s3Key).toString();
        URL url =  s3Client.getUrl(bucketName,s3Key);
        return url.toString();
    }
}
