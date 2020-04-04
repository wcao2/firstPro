package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller call business layer
@RestController
@RequestMapping(value={"/departments","/depts"})//this is a uri
public class DepartmentController {
    @Autowired private DepartmentService departmentService;
    //@Autowired private Logger logger;   TODO


    //{prefix}/departments GET    //11111111111111111111
    @RequestMapping(value="/list/eager",method = RequestMethod.GET)
    public List<Department> getDepartments(){
        List<Department> dept=departmentService.getDepartments();
        return dept;
    }
    //{prefix}/departments GET
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public List<Department> getDepartmentsLazy(){
        List<Department> dept=departmentService.getDepartmentsLazy();
        return dept;
    }

    //{prefix}/departments GET    TEST PASS
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    //pass variable 尽量用id
    public Department getDeptBy(@PathVariable("id") Long id){
        return departmentService.getDepartmentLazyBy(id);
    }

    //{prefix}/department GET             TEST PASS
    @RequestMapping(value ="",method = RequestMethod.GET)
    public Department getDepartmentByName(@RequestParam("deptName") String deptName){
        Department department=departmentService.getDepartmentByName(deptName);
        return department;
    }

    //{prefix}/departments POST   TEST PASS
    @RequestMapping(value="",method = RequestMethod.POST)
    public Department createDepartment(@RequestBody Department department){
        String msg;
        Department d=departmentService.save(department);
        if(d==null) msg="The department was not created";
        return d;
    }

    //{prefix}/departments PUT    //PASS
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public String updateDepartment(@RequestBody Department department){
        //can not update deptName
        Department d1=departmentService.getDepartmentByName(department.getName());
        d1.setName(department.getName());
        d1.setLocation(department.getLocation());
        d1.setDescription(department.getDescription());
        //departmentService.delete(department.getId());
        boolean isSuccess=departmentService.update(d1);//should get the obj first then update
        String msg=null;
        if(isSuccess){
            msg="the department is updated";
        }else{
            msg="the department is failure to update";
        }
        return msg;
    }

    //{prefix}/departments DELETE   //PASS
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String deleteDepartment(@PathVariable("id") Long id){//should be id
        String msg=null;
        boolean isSuccess=departmentService.delete(id);
        if(isSuccess){
            msg="the department is successfully deleted";
        }else{
            msg="failure to delete department";
        }
        return msg;
    }
}
