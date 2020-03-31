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

    public List<Department> getDepartments(){
        return departmentDao.getDepartments();
    }

    public List<Department> getDepartmentsEager(){
        return departmentDao.getDepartmentsEager();
    }

    public Department getDepartmentEagerBy(Long id){
        return departmentDao.getDepartmentEagerBy(id);
    }

    public Department getDepartmentLazyBy(Long id){
        return departmentDao.getDepartmentLazyBy(id);
    }

    public boolean delete(Department dep){
        return departmentDao.delete(dep);
    }

    public boolean update(Department dep){
        return departmentDao.update(dep);
    }

    public Department getDepartmentByName(String deptName){
        return departmentDao.getDepartmentByName(deptName);
    }


}
