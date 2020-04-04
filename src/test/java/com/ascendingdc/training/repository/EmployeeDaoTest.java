package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;
    //private String deptName="HR";

    @Before
    public void init(){

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
}
