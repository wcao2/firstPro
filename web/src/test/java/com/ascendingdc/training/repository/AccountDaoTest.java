package com.ascendingdc.training.repository;


import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;

    @Before
    public void init(){
        System.out.println("=============================== test started===============================");
    }

    @After
    public void tearDown(){
        System.out.println("=============================== test finished===============================");
    }

    @Test   //Pass
    public void getAccountByIdTest(){
        Account account = accountDao.getAccountById(1L);
        Assert.assertEquals(1L,account.getId().longValue());
        Assert.assertEquals("normal",account.getAccount_type());
    }

    @Test   //Pass
    public void saveTest(){
        Account account = accountDao.getAccountById(1L);
        Employee e=account.getEmployee();
        Account a=new Account("credit", BigDecimal.valueOf(10.00),e);
        Account a1=accountDao.save(a);
        Assert.assertEquals("credit",a1.getAccount_type());
    }

    @Test   //Pass
    public void deleteTest(){
        boolean bool=accountDao.delete(1L);
        Assert.assertTrue(bool);
    }

}
