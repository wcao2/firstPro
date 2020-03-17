package com.ascendingdc.training.jdbc;

import com.ascendingdc.training.model.EmployeeJDBC;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EmployeeJDBCDaoTest {
    private EmployeeJDBCDao employeeJDBCDao;

    @Before
    public void init(){employeeJDBCDao=new EmployeeJDBCDao();}

    @After
    public void teardown(){
        System.out.println("================================finished test===============================");
    }

    @Test
    public void getEmployeesTest(){
        List<EmployeeJDBC> employees=employeeJDBCDao.getEmployees();
        int expectNum=0;

        Assert.assertEquals(expectNum,employees.size());
    }
}
