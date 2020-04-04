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

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
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
        Employee e=employeeService.save(employee,deptName);
        Assert.assertEquals("Jenny",e.getFirst_name());
    }

    @Test
    public void getEmployeeByNameTest(){
        String deptName="Jenny Smith";
        Employee employee=employeeService.getEmployeeByName(deptName);
        Assert.assertEquals(deptName,employee.getName());
    }

    @Test
    public void deleteTest(){
        boolean var=employeeService.delete(1L);
        Assert.assertTrue(var);
    }

    @Test   //TEST PASS
    public void getEmployeesAndDeptTest(){
        List<Employee> employees=employeeService.getEmployeesAndDept();
        int expectNum=4;
        Assert.assertEquals(expectNum,employees.size());
    }

    @Test
    public void updateEmployeeAddressTest(){
        String employeeName="Jason";
        Employee employee=employeeService.getEmployeeByName(employeeName);
        employee.setAddress("fairfax road");
        int num=employeeService.updateEmployeeAddress(employee.getName(),employee.getAddress());
        Assert.assertEquals(1,num);
    }
}
