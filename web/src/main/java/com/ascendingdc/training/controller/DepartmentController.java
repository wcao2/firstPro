package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.views.JsView;
import com.ascendingdc.training.model.views.JsView1;
import com.ascendingdc.training.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller call business layer
@RestController
@RequestMapping(value={"/departments","/depts"})//this is a uri
public class DepartmentController {

    @Autowired private DepartmentService departmentService;

    //{prefix}/departments GET        1:getDepartmentById(id)
    @RequestMapping(value="/{id}",method = RequestMethod.GET)    //pass variable 尽量用id
    @JsonView({JsView.User.class})
    public Department getDepartmentById(@PathVariable("id") Long id){
        return departmentService.getDepartmentById(id);
    }

    //{prefix}/department GET              2:getDepartmentByName
    @RequestMapping(value ="",method = RequestMethod.GET)
    @JsonView(JsView.User.class)
    public Department getDepartmentByName(@RequestParam("deptName") String deptName){
        Department department=departmentService.getDepartmentByName(deptName);
        return department;
    }

    //{prefix}/departments GET   3:
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @JsonView(JsView.User.class)
    public List<Department> getDepartmentsLazy(){
        List<Department> dept=departmentService.getDepartmentsLazy();
        return dept;
    }

    //{prefix}/departments GET   //4
    @RequestMapping(value="/list/eager",method = RequestMethod.GET)
    @JsonView(JsView1.User1.class)
    public List<Department> getDepartmentsEager(){
        List<Department> dept=departmentService.getDepartmentsEager();
        return dept;
    }


    //{prefix}/departments POST
    @RequestMapping(value="",method = RequestMethod.POST)
    @JsonView(JsView.User.class)
    public Department createDepartment(@RequestBody Department department){
        String msg;
        Department d=departmentService.save(department);
        if(d==null) msg="The department was not created";
        return d;
    }

    //{prefix}/departments PUT
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @JsonView(JsView.User.class)
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

    //{prefix}/departments DELETE
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


























