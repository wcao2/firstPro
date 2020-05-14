package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.service.AccountService;
import com.ascendingdc.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value={"/accounts"})//this is a uri
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmployeeService employeeService;

    //{prefix}/accounts GET
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") Long id){
        Account account=accountService.getAccountById(id);
        return account;
    }

    //{prefix}/accounts POST
    @RequestMapping(value="",method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account){
        Employee employee=employeeService.getEmployeeByName("dwang");
        account.setEmployee(employee);
        Account a=accountService.save(account);
        return a;
    }

    //{prefix}/accounts DELETE
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("id") Long id){
        String msg=null;
        boolean isSuccess=accountService.delete(id);
        if(isSuccess){
            msg="the employee is successfully deleted";
        }else{
            msg="failure to delete department";
        }
        return msg;
    }
}


















