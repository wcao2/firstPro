package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.model.views.JsView;
import com.ascendingdc.training.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value={"/employees","/empl"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //{prefix}/employees GET    //TEST passed
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @JsonView({JsView.User.class})
    public List<Employee> getEmployeesAndDept(){
        return employeeService.getEmployeesAndDept();
    }

    //{prefix}/employees GET  //pass
    @RequestMapping(value="",method = RequestMethod.GET)
    @JsonView({JsView.User.class})
    public Employee getEmployeeByName(@RequestParam("employeeName") String employeeName){
        Employee employee= employeeService.getEmployeeByName(employeeName);
        return employee;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @JsonView({JsView.User.class})
    public Employee getEmployeeById(@PathVariable("id") Long Id){
        Employee employee=employeeService.getEmployeeById(Id);
        return employee;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @JsonView({JsView.User.class})
    public Employee updateEmployeeAddress(@PathVariable("id") Long Id,@RequestParam("email") String email){
        Employee employee=employeeService.getEmployeeById(Id);
        employee.setEmail(email);
        return employeeService.updateEmployeeEmail(employee);
    }

    //{prefix}/employees DELETE   //PASS
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Long id){
        String msg=null;
        boolean isSuccess=employeeService.delete(id);
        if(isSuccess){
            msg="the employee is successfully deleted";
        }else{
            msg="failure to delete employee";
        }
        return msg;
    }

    //{prefix}/employees POST                    //pass
    @RequestMapping(value="",method = RequestMethod.POST)
    @JsonView({JsView.User.class})
    public Employee createEmployee(@RequestBody Employee employee){
        String msg;
        Employee e=employeeService.save(employee,"R&D");
        if(e==null){
            return null;
        }else{
            return e;
        }
    }
}




















