package com.ascendingdc.training.repository;

import com.ascendingdc.training.init.AppBootstrap;
import com.ascendingdc.training.model.Role;
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
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;

    @Before
    public void init(){
        System.out.println("=============================== test started===============================");
    }

    @After
    public void tearDown(){
        System.out.println("================================finished test===============================");
    }

    @Test  //pass   13L被删除了已经
    public void saveRoleTest(){
        Role r=new Role("normal","A",true,false,false,false);
        Role role=roleDao.save(r);
        Assert.assertEquals("normal",r.getName());
    }

    @Test  //pass
    public void getDepartmentsTest(){
        List<Role> roles=roleDao.getRoles();
        int expectedNum=4;
        Assert.assertEquals(expectedNum,roles.size());
    }

    @Test    //pass
    public void getRoleByIdTest(){
        Role role=roleDao.getById(14L);
        Assert.assertNotNull(role);
        Assert.assertEquals(14l, role.getId().longValue());
    }

    @Test
    public void deleteByIdTest(){
        Role role=roleDao.getById(13L);
        int num=roleDao.deleteById(role);
        Assert.assertEquals(1,num);
    }

    @Test
    public void updateRoleTest(){
        Role role=roleDao.getById(16L);
        role.setAllowedResource("c://file system/ascending/my programs");
        int num=roleDao.update(role);
        Assert.assertEquals(1,num);
    }

}
