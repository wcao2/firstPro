package com.ascendingdc.training.jdbc;

import com.ascendingdc.training.model.DepartmentJDBC;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DepartmentJDBCDaoTest{
    private DepartmentJDBCDao departmentDao;

    @Before
    public void init(){ departmentDao=new DepartmentJDBCDao(); }

    @After
    public void teardown(){

    }

    //this is a test case
    @Test
    public void getDepartmentsTest(){
        List<DepartmentJDBC> departments=departmentDao.getDepartments();
        int expectNum=0;

        Assert.assertEquals(expectNum,departments.size());
    }

}