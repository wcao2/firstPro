package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Before
    public void init(){
        System.out.println("================================start test===============================");
    }

    @After
    public void tearDown(){
        System.out.println("================================finished test===============================");
    }

    @Test
    public void save(){
        //for save
        Employee employee=new Employee("Jenny Smith", "Jenny","Smith","smith@gmu.edu","NewYork");
        String deptName="R&D";
        boolean var=employeeService.save(employee,deptName);
        Assert.assertTrue(var);
    }

    @Test
    public void getEmployeeByName(){
        String deptName="Jenny Smith";
        Employee employee=employeeService.getEmployeeByName(deptName);
        Assert.assertEquals(deptName,employee.getName());
    }

    @Test
    public void delete(){
        String employeeName="Jenny Smith";
        Employee employee=employeeService.getEmployeeByName(employeeName);
        boolean var=employeeService.delete(employee);
        Assert.assertTrue(var);
    }

    @Test
    public void getEmployees(){
        List<Employee> employees=employeeService.getEmployees();
        int expectNum=4;
        Assert.assertEquals(expectNum,employees.size());
    }

    @Test
    public void updateEmployeeAddress(){
        String employeeName="Jason";
        Employee employee=employeeService.getEmployeeByName(employeeName);
        employee.setAddress("fairfax road");
        int num=employeeService.updateEmployeeAddress(employee.getName(),employee.getAddress());
        Assert.assertEquals(1,num);
    }
}
