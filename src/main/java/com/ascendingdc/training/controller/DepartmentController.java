package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value={"/departments","/depts"})
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //{prefix}/department GET
    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    //{prefix}/department GET         TODO
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Department getDeptBy(@PathVariable("id") Long id){
        return departmentService.getDepartmentLazyBy(id);
    }

    //{prefix}/department GET
//    @RequestMapping(value ="/{deptName}",method = RequestMethod.GET)
//    public Department getDepartment(@PathVariable String deptName){
//        Department department=departmentService.getDepartmentByName(deptName);
//        return department;
//    }

    //{prefix}/department POST
    @RequestMapping(value="",method = RequestMethod.POST)
    public String createDepartment(@RequestBody Department department){
        String msg=null;
        Department d=departmentService.save(department);
        if(d!=null)
            msg="The department is created";
        return msg;
    }

    //{prefix}/department PUT
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public String updateDepartment(@RequestBody Department department){
        boolean isSuccess=departmentService.update(department);
        String msg=null;
        if(isSuccess){
            msg="the department is created";
        }else{
            msg="the department is failure to create";
        }
        return msg;
    }

    //{prefix}/department DELETE
    @RequestMapping(value="",method = RequestMethod.DELETE)
    public String deleteDepartment(@RequestBody Department department){
        String msg=null;
        boolean isSuccess=departmentService.delete(department);
        if(isSuccess){
            msg="the department is successfully deleted";
        }else{
            msg="failure to delete department";
        }
        return msg;
    }
}
