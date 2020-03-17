package com.ascendingdc.training.jdbc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountJDBCDaoTest {
    private AccountJDBCDao accountJDBCDao;

    @Before
    public void init(){accountJDBCDao=new AccountJDBCDao();}

    @After
    public void teardown(){
        System.out.println("================================finished test===============================");
    }

    @Test
    public void getAccountTest(){
        boolean marker=accountJDBCDao.getAccounts();
        Assert.assertTrue(marker);
    }
}
