package com.ascendingdc.training.repository;

import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.model.Image;
import com.ascendingdc.training.model.Role;
import com.ascendingdc.training.service.EmployeeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBootstrap.class)
public class ImageDaoTest {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleDao roleDao;

    private Employee testEmployee=null;

    @Before
    public void init(){
        testEmployee=new Employee();
        testEmployee.setName("JinPing");
        testEmployee.setEmail("ping@gmu.edu");
        List<Role> roles=new ArrayList<Role>();
        roles.add(roleDao.getById(2L));
        testEmployee.setRoles(roles);
        employeeService.save(testEmployee,"R&D");
    }

    @After
    public void tearDown(){
         employeeService.delete(testEmployee.getId());
    }

    @Test
    public void saveTest(){
        Image img=new Image("1.txt","ASDQWEZXC", LocalDateTime.now(),testEmployee);
        Image image=imageDao.save(img);
        Assert.assertEquals("ASDQWEZXC",image.getS3Key());
    }

    @Test
    public void getByEmployeeIdTest(){
        List<Image> images=imageDao.getByEmployeeId(testEmployee.getId());
        Assert.assertEquals(1,images.size());
    }

    @Test
    public void delByEmployeeIdTest(){
        Employee employee = employeeService.getEmployeeByName("JinPing");
        int i = imageDao.delByUserId(employee.getId());
        Assert.assertEquals(0,i);
    }
}






















