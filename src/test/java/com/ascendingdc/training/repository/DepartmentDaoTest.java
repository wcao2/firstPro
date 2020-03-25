package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DepartmentDaoTest {
    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;

    private Employee e1;
    //private Employee e2;
//    private Department d1;
//    private String depString="HR3";

    @Before
    public void init(){
        departmentDao=new DepartmentDaoImpl();
//        d1=new Department();
//        d1.setName(depString);
//        d1.setDescription("random desc");
//        d1.setLocation("US");
//        d1=departmentDao.save(d1);
//
        employeeDao=new EmployeeDaoImpl();
//        e1=new Employee();
//        e1.setName("zhang3");
//        e1.setAddress("us");
//        e1.setDepartment(d1);
//        employeeDao.save(e1, d1.getName());
    }

    @After
    public void tearDown(){
       // departmentDao.delete(d1);
//        employeeDao.delete(e1);
//        departmentDao.delete(d1);    //TODO
        System.out.println("================================finished test===============================");
    }

//    @Test
//    public void getDepartmentsTest(){
//        List<Department> departments=departmentDao.getDepartments();
//        int expectedNum=4;
//        Assert.assertEquals(expectedNum,departments.size());
//    }

//    @Test
//    public void getDepartmentEagerByTest(){
//        Department department=departmentDao.getDepartmentEagerBy(d1.getId());
//        Assert.assertNotNull(department);
//        Assert.assertEquals(department.getName(),d1.getName());
//        Assert.assertTrue(department.getEmployee().size()>0);
//    }
//
//    @Test
//    public void getDepartmentLazyByTest(){
//        Department department=departmentDao.getDepartmentLazyBy(d1.getId());
//        System.out.println("======id========"+d1.getId());
//        Assert.assertNotNull(department);
//        Assert.assertEquals(department.getName(),d1.getName());
//        //Assert.assertTrue(department.getEmployee().size()==0);
//    }

//    @Test
//    public void updateDepartmentTest(){
//        Department oldDepartment = departmentDao.getDepartmentByName("R&D");
//        oldDepartment.setLocation("China BeiJing");
//        departmentDao.update(oldDepartment);
//        Assert.assertEquals(oldDepartment.getLocation(), "China BeiJing");
//    }

//    @Test
//    public void getDepartmentByName(){
//        String deptName="HR1";
//        Department department=departmentDao.getDepartmentByName(deptName);
//        Assert.assertEquals(deptName,department.getName());
//    }

    @Test
    public void deleteDepartmentTest(){
         Department department=departmentDao.getDepartmentByName("lalala");
         Assert.assertTrue(departmentDao.delete(department));

    }
}
