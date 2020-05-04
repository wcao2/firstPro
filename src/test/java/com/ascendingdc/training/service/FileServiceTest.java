package com.ascendingdc.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ascendingdc.training.init.AppBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    //verify mock has invoked putObject() method 1 time;
    @Autowired
    private AmazonS3 s3Client;

    @Test
    public void uploadFileTest1(){
        //1:this is an integration test, should be rewrite to unit test
        File testFile1=new File("/home/weicao/Downloads/sampleFile3.txt");
        fileService.uploadFile(testFile1);
    }

    @Test
    public void uploadFileTest2(){
        AmazonS3 s3Client1=mock(AmazonS3.class);//创建一个新的AmazonS3 client

        s3Client1.putObject("xxx","xxx","string of object");
        verify(s3Client1,times(1)).putObject(anyString(),anyString(),anyString());
    }

    @Test
    public void uploadFileTest3(){
        File testFile=mock(File.class);
        when(testFile.getName()).thenReturn("sample.txt");

        fileService.uploadFile(null);
        verify(s3Client,times(0)).putObject(any(PutObjectRequest.class));
        fileService.uploadFile(testFile);
        verify(s3Client,times(1)).putObject(any(PutObjectRequest.class));
    }



    //real test
    @Test
    public void uploadFileTest() throws MalformedURLException, IOException {
        MultipartFile testFile=mock(MultipartFile.class);//不再需要找文件 则file一定不是null
        ObjectMetadata mockData = mock(ObjectMetadata.class);
        //File t1= Mockito.mock(File.class);
        when(testFile.getInputStream()).thenReturn(mock(InputStream.class));
        when(mockData.getContentType()).thenReturn("content-type");
        when(testFile.getOriginalFilename()).thenReturn("sampleFile.txt");//stub: 总返回这个 不管怎么调用 为了执行service接下来的语句

        fileService.uploadFile("XXX",testFile);
        //verify 基于mock的对象 而不是fileservice 里的s3Client
        verify(s3Client,times(1)).putObject(anyString(),anyString(),any(InputStream.class),any());//spies:verify
    }

    @Test
    //验证的是behavior
    public void getUrlTest() throws MalformedURLException {
        when(s3Client.getUrl(anyString(),anyString())).thenReturn(new URL("http","xxx",123,"xxx"));//调用toString()不会为空
        fileService.getUrl("xxx","xxx");
        verify(s3Client,times(1)).getUrl(anyString(),anyString());
    }
}









