package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.model.Role;
import com.ascendingdc.training.model.views.JsView;
import com.ascendingdc.training.repository.RoleDao;
import com.ascendingdc.training.service.EmployeeService;
import com.ascendingdc.training.service.JWTService;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value={"/auth"})
public class AuthController {
    @Autowired private EmployeeService employeeService;
    @Autowired private JWTService jwtService;
    @Autowired private RoleDao roleDao;

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
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("please input email or username");
            }
            //return token is json format
            if(emp==null) return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();//todo
            Map<String,String> map=new HashMap<>();
            map.put("token",jwtService.generateToken(emp));//        call method in JWTService
            return ResponseEntity.ok().body(map);//body自带build function
        }catch (Exception e){
            e.printStackTrace();
        }
        //if there are any exception
        return ResponseEntity.badRequest().build();
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    @JsonView(JsView.Admin.class)
    public ResponseEntity userSignUp(@RequestBody Employee employee){
        Employee emp=null;//todo
        if(employee.getEmail()==null||employee.getName()==null||employee.getPassword()==null){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("please type necessary information");
        }else{
            List<Role> roles=new ArrayList<>();
            Role r=roleDao.getById(2L);
            roles.add(r);
            employee.setRoles(roles);
            Employee e=employeeService.save(employee,"R&D");
            if(e==null) return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(e);
            //return ResponseEntity.status(HttpServletResponse.SC_OK).build();
        }
    }


}
