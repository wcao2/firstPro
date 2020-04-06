package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.repository.DepartmentDao;
import com.ascendingdc.training.repository.DepartmentDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public Department save(Department department){
        return departmentDao.save(department);
    }

    //1: get DeptById
    public Department getDepartmentById(Long id){
        return departmentDao.getDepartmentById(id);
    }

    //2:get DeptByName
    public Department getDepartmentByName(String deptName){
        return departmentDao.getDepartmentByName(deptName);
    }

    //3
    public List<Department> getDepartmentsLazy(){
        return departmentDao.getDepartmentsLazy();
    }

    //4
    public List<Department> getDepartmentsEager(){
        return departmentDao.getDepartmentsEager();
    }

    public Department getDepartmentEagerBy(Long id){
        return departmentDao.getDepartmentEagerBy(id);
    }


    public boolean delete(Long id){
        return departmentDao.delete(id);
    }

    public boolean update(Department dep){return departmentDao.update(dep);}



}

















