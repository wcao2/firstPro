package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.service.EmployeeService;
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
    public List<Employee> getEmployeesAndDept(){
        return employeeService.getEmployeesAndDept();
    }

    //{prefix}/employees GET  //pass
    @RequestMapping(value="",method = RequestMethod.GET)
    public Employee getEmployeeByName(@RequestParam("employeeName") String employeeName){
        Employee employee= employeeService.getEmployeeByName(employeeName);//?????????????????????????????????????????
        return employee;
    }



    //{prefix}/employees DELETE   //PASS
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Long id){
        String msg=null;
        boolean isSuccess=employeeService.delete(id);
        if(isSuccess){
            msg="the employee is successfully deleted";
        }else{
            msg="failure to delete department";
        }
        return msg;
    }

    //{prefix}/employees POST                    //pass
    @RequestMapping(value="",method = RequestMethod.POST)
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
