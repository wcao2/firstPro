package com.ascendingdc.training.repository;


import com.ascendingdc.training.model.Employee;

import java.util.List;

public interface EmployeeDao{
    Employee save(Employee employee,String deptName);
    Employee updateEmployeeEmail(Employee e);

    List<Employee> getEmployeesAndDept();
    Employee getEmployeeById(Long Id);
    Employee getEmployeeByName(String employeeName);

    boolean delete(Long id);
}
