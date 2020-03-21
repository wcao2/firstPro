package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;

import java.util.List;

public interface DepartmentDao {
    Department save(Department department);
//    Department update(Department department);
//    boolean delete(String deptName);
    boolean delete(Department dep);
//    Department getDepartmentByName(String deptName);
    List<Department> getDepartments();

    List<Department> getDepartmentWithChildren();
    //take out department, and take all of the employees related to this department
    Department getDepartmentEagerBy(Long id);
    Department getDepartmentLazyBy(Long id);
}
