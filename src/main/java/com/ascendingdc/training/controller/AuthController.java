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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value={"/auth"})
public class AuthController {
    @Autowired private EmployeeService employeeService;
    @Autowired private JWTService jwtService;

    private Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping(value="",method=RequestMethod.POST)
    //ResponseEntity return token and status code
    public ResponseEntity userLogin(@RequestBody Employee employee){//jackson Serialization only can do set(from postman)
        try {
            Employee emp=null;
            //login with username or email
            if(employee.getName()==null&&employee.getEmail()!=null){
                emp=employeeService.getEmployeeByCredentials(employee.getEmail(),employee.getPassword());
            }else if(employee.getEmail()==null&&employee.getName()!=null){
                emp=employeeService.getEmployeeByCredentials(employee.getName(),employee.getPassword());
            }else{
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();//HOW TO ADD A MESSAGE SEND TO FRONT
            }
            //return token is json format
            if(emp==null) return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            Map<String,String> map=new HashMap<>();
            map.put("token",jwtService.generateToken(emp));
            return ResponseEntity.ok().body(map);//body自带build function
        }catch (Exception e){
            e.printStackTrace();
        }
        //if there are any exception
        return ResponseEntity.badRequest().build();
    }
}
