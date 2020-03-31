package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.repository.EmployeeDao;
import com.ascendingdc.training.repository.EmployeeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public boolean save(Employee emp, String deptName ){
        return employeeDao.save(emp,deptName);
    }

    public int updateEmployeeAddress(String name,String address){
        return employeeDao.updateEmployeeAddress(name,address);
    }

    public List<Employee> getEmployees(){
        return employeeDao.getEmployees();
    }

    public Employee getEmployeeByName(String deptName){
        return employeeDao.getEmployeeByName(deptName);
    }

    public boolean delete(Employee emp){
        return employeeDao.delete(emp);
    }
}
