package com.ascendingdc.training.service;


import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;

    @Before
    public void init(){
        System.out.println("==============test started=======================");
    }

    @Test
    public void generateTokenTest(){
        Employee e= new Employee();
        e.setId(1L);
        e.setName("dwang");
        String token=jwtService.generateToken(e);
        String[] array = token.split("\\.");
        boolean bool= array.length==3?true:false;
        Assert.assertTrue(bool);
        //Assert.notNull(token);
    }

    @After
    public void tearDown(){
        System.out.println("==============test finished=======================");
    }
}
