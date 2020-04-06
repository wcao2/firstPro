package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.repository.EmployeeDao;
import com.ascendingdc.training.repository.EmployeeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee save(Employee emp, String deptName ){
        return employeeDao.save(emp,deptName);
    }

    public Employee updateEmployeeEmail(Employee e){
        return employeeDao.updateEmployeeEmail(e);
    }

    public List<Employee> getEmployeesAndDept(){
        return employeeDao.getEmployeesAndDept();
    }

    public Employee getEmployeeByName(String employeeName){
        return employeeDao.getEmployeeByName(employeeName);
    }

    public boolean delete(Long id){
        return employeeDao.delete(id);
    }

    public Employee getEmployeeById(Long id){
        return employeeDao.getEmployeeById(id);
    }
}

























