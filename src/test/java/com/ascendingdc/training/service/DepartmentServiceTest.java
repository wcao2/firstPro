package com.ascendingdc.training.service;


import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.repository.DepartmentDao;
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
public class DepartmentServiceTest {


    @Autowired
    private DepartmentService departmentService;

    @Before
    public void init(){
        System.out.println("================================start test===============================");
    }

    @After
    public void tearDown(){
        System.out.println("================================finished test===============================");
    }

    //must be remove ignore in Department
    @Test
    public void saveDepartment(){
        Department department=new Department("entertainment","built by Jason","Xi'an China");
        department=departmentService.save(department);
        Assert.assertEquals("entertainment",department.getName());
    }

    @Test
    public void deleteDepartment(){
        Department department=departmentService.getDepartmentByName("entertainment");
        boolean var=departmentService.delete(department);
        Assert.assertTrue(var);
    }

    @Test
    public void getDepartment(){
        List<Department> departments=departmentService.getDepartments();
        int expectedNumofDept=2;
        Assert.assertEquals(expectedNumofDept,departments.size());
    }

    @Test
    public void getDepartmentsEager(){
        List<Department> departments=departmentService.getDepartmentsEager();
        int expectedNumofDept=2;
        Assert.assertEquals(expectedNumofDept,departments.size());
    }

    @Test
    public void getDepartmentEagerById(){
        Long id=2L;
        Department department=departmentService.getDepartmentEagerBy(id);
        Assert.assertEquals(id,department.getId());
    }

    @Test
    public void getDepartmentLazyById(){
        Long id=2L;
        Department department=departmentService.getDepartmentLazyBy(id);
        Assert.assertEquals(id,department.getId());
    }

    @Test
    public void getDepartmentByName(){
        String deptName="R&D";
        Department department=departmentService.getDepartmentByName(deptName);
        Assert.assertEquals("R&D",department.getName());
    }

    @Test
    public void updateDepartmentLocation(){
        String deptName="R&D";
        String location="Beijing ChangAn Road 113";
        Department department=departmentService.getDepartmentByName(deptName);
        department.setLocation(location);
        departmentService.update(department);
        department=departmentService.getDepartmentByName("R&D");
        Assert.assertEquals("Beijing ChangAn Road 113",department.getLocation());
    }
}
