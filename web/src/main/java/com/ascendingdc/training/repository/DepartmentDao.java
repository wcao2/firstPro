package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;

import java.util.List;

public interface DepartmentDao {
    Department save(Department department);
    //1
    Department getDepartmentById(Long id);
    //2
    Department getDepartmentByName(String deptName);
    //3
    List<Department> getDepartmentsLazy();
    //4
    List<Department> getDepartmentsEager();

    boolean delete(Long id);//delete
    boolean update(Department dep);//Update

    Department getDepartmentEagerBy(Long id);//take out department, and take all of the employees related to this department



}




















