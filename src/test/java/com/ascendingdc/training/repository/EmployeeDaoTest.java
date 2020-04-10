package com.ascendingdc.training.repository;

import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Department;
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
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;
    //private String deptName="HR";

    @Before
    public void init(){
        System.out.println("================================start test===============================");
    }

    @After
    public void tearDown(){
        System.out.println("================================finished test===============================");
    }

//    @Test
//    Pass
//    public void save(){
//        Employee employee=new Employee("Jenny","wei12","cao12","wcao212@gmu.edu","sunside road");
//        employeeDao.save(employee,"R&D");
//        List<Employee> employees=employeeDao.getEmployees();
//        int expectedNum=3;
//        Assert.assertEquals(expectedNum,employees.size());
//    }
    @Test
    public void getEmployeesAndDeptTest(){
        List<Employee> employees=employeeDao.getEmployeesAndDept();
        int expectedNum=1;
        Assert.assertEquals(expectedNum,employees.size());
    }

//    @Test
//    PASS
//    public void deleteEmployee(){
//        Employee e=employeeDao.getEmployeeByName("Jenny");
//        boolean success=employeeDao.delete(e);
//        Assert.assertTrue(success);
//    }

    @Test
    public void getEmployeeByCredentialsTest() throws Exception {
        String email="xyhuang@training.ascendingdc.com";
        String password="25f9e794323b453885f5181f1b624d0b";
        Employee e = employeeDao.getEmployeeByCredentials(email,password);
        Assert.assertEquals(e.getEmail(),"xyhuang@training.ascendingdc.com");
    }
}
