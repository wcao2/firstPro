package com.ascendingdc.training.repository;


import com.ascendingdc.training.model.Employee;

import java.util.List;

public interface EmployeeDao{
    Employee save(Employee employee,String deptName);
    int updateEmployeeAddress(String name,String address);
    List<Employee> getEmployeesAndDept();

    Employee getEmployeeByName(String employeeName);
    boolean delete(Long id);
}
