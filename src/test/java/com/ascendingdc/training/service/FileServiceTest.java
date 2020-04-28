package com.ascendingdc.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ascendingdc.training.init.AppBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
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
    private AmazonS3 s3Client;//一定是基于mock的对象 不会上传上去

    @Test
    public void uploadFileTest(){

        //2:把dependency injection的bean变成mock 在调用uploadFile时候 会验证bean在putobject会被调一次(s3Client.putObject(req))
        //不需要 @Autowired private AmazonS3 s3Client;
//        AmazonS3 s3Client=mock(AmazonS3.class);//创建一个新的AmazonS3 client
//        s3Client.putObject("xxx","xxx","string of object");
//        verify(s3Client,times(1)).putObject(anyString(),anyString(),anyString());

        //3:real test
        File testFile=mock(File.class);//不再需要找文件 则file一定不是null
        //File t1= Mockito.mock(File.class);

        //null 进来什么都不做
        fileService.uploadFile(null);
        verify(s3Client,times(0)).putObject(any(PutObjectRequest.class));
        //File testFile=new File("/home/weicao/Downloads/sampleFile1.txt");
        when(testFile.getName()).thenReturn("sampleFile.txt");//stub: 总返回这个 不管怎么调用 为了执行service接下来的语句
        fileService.uploadFile(testFile);
        //verify 基于mock的对象 而不是fileservice 里的s3Client
        verify(s3Client,times(1)).putObject(any(PutObjectRequest.class));//spies:verify

        //1.1
        //fileService.uploadFile();

        //1.2 this is an integration test, should be rewrite to unit test
//        File testFile=new File("/home/weicao/Downloads/sampleFile3.txt");
//        fileService.uploadFile(testFile);
    }

    @Test
    //验证的是behavior
    public void getUrlTest() throws MalformedURLException {
        when(s3Client.getUrl(anyString(),anyString())).thenReturn(new URL("http","xxx",123,"xxx"));//调用toString()不会为空
        fileService.getUrl("zhangsan3");
        verify(s3Client,times(1)).getUrl(anyString(),anyString());
    }
}
