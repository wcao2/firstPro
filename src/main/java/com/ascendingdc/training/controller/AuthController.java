package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.service.EmployeeService;
import com.ascendingdc.training.service.JWTService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value={"/auth"})
public class AuthController {
    @Autowired private EmployeeService employeeService;
    @Autowired private JWTService jwtService;

    private Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody Employee employee){//jackson Serialization only can do set(from postman)
        try {
            Employee emp=null;
            //login with username or email
            if(employee.getName()==null){
                emp=employeeService.getEmployeeByCredentials(employee.getEmail(),employee.getPassword());
            }else{
                emp=employeeService.getEmployeeByCredentials(employee.getName(),employee.getPassword());
            }
            //return token is json format
            if(emp==null) return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            return ResponseEntity.ok().body(jwtService.generateToken(emp));//body自带build function
        }catch (Exception e){
            e.printStackTrace();
        }
        //if there are any exception
        return ResponseEntity.badRequest().build();
    }
}
