package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;

import java.util.List;

public interface DepartmentDao {
    Department save(Department department);//create
    boolean delete(Department dep);//delete
    boolean update(Department dep);//Update

    Department getDepartmentEagerBy(Long id);//take out department, and take all of the employees related to this department
    Department getDepartmentLazyBy(Long id);//only take department

    List<Department> getDepartments();
    List<Department> getDepartmentsEager();
    Department getDepartmentByName(String deptName);//by using deptName to get department obj
}
