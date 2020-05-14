package com.ascendingdc.training.repository;

import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import org.hibernate.LazyInitializationException;
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
public class DepartmentDaoTest {
    //every Spring injection use it
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

    private Department d1;

    @Before
    public void init(){
        departmentDao=new DepartmentDaoImpl();
        d1=new Department();
        d1.setName("depTest");
        d1.setDescription("random desc");
        d1.setLocation("US");
        d1=departmentDao.save(d1);
    }

    @After
    public void tearDown(){
        departmentDao.delete(d1.getId());
        System.out.println("================================finished test===============================");
    }


    @Test
    public void getDepartmentEagerByTest(){
        Department department=departmentDao.getDepartmentEagerBy(1L);
        Assert.assertNotNull(department);
        Assert.assertEquals(department.getName(),"Human Resource");
        Assert.assertTrue(department.getEmployee().size()>0);
    }

    @Test
    public void getDepartmentLazyByTest(){
        List<Department> depts=departmentDao.getDepartmentsLazy();
        Assert.assertEquals(3,depts.size());
    }

    @Test
    public void updateDepartmentTest(){
        Department oldDepartment = departmentDao.getDepartmentByName(d1.getName());
        oldDepartment.setLocation("China BeiJing");
        departmentDao.update(oldDepartment);
        Assert.assertEquals(oldDepartment.getLocation(), "China BeiJing");
    }

    @Test
    public void getDepartmentByName(){
        String deptName=d1.getName();
        Department department=departmentDao.getDepartmentByName(deptName);
        Assert.assertEquals(deptName,department.getName());
    }

    @Test
    public void deleteDepartmentTest(){
         Department department=departmentDao.getDepartmentByName(d1.getName());
         Assert.assertTrue(departmentDao.delete(department.getId()));

    }
}
