package com.ascendingdc.training.repository;


import com.ascendingdc.training.model.Employee;

import java.util.List;

public interface EmployeeDao{
    boolean save(Employee employee,String deptName);
    int updateEmployeeAddress(String name,String address);
    List<Employee> getEmployees();
    Employee getEmployeeByName(String name);
    boolean delete(Employee e);

}
