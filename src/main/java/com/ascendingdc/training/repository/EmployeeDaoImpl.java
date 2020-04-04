package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private DepartmentDao departmentDao;
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    //pass
    public Employee save(Employee employee, String deptName) {
        Transaction transaction=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
                Department department=departmentDao.getDepartmentByName(deptName);//TODO
                if(department!=null){
                    transaction=session.beginTransaction();
                    employee.setDepartment(department);
                    session.save(employee);
                    transaction.commit();
                    session.close();
                    return employee;
                }else{
                    logger.error("the department name does not exist");
                    return null;
                }
        }
        catch (Exception exception){
            if(transaction!=null) transaction.rollback();
            logger.error(exception.getMessage());
            session.close();
            return null;
        }
    }

    @Override
    public int updateEmployeeAddress(String name, String address) {
        String hql="update Employee as em set em.address=:address where em.name=:name";
        int updatedCount;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Employee> query=session.createQuery(hql);
            query.setParameter("name",name);
            query.setParameter("address",address);
            transaction=session.beginTransaction();
            updatedCount=query.executeUpdate();
            transaction.commit();
            session.close();
            return updatedCount;
        }catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
            session.close();
            return 0;
        }
    }

    @Override
    //PASS
    public boolean delete(Long id) {
        String hql="DELETE Employee as emp where emp.id=:Id";
        int deletedCount;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Employee> query=session.createQuery(hql);
            query.setParameter("Id",id);
            deletedCount=query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount>=1?true:false;
        }
        catch (HibernateException exception){
            if(transaction!=null) transaction.rollback();
            session.close();
            logger.error("unable to delete record",exception);
        }
        return false;
    }

    //pass
    public List<Employee> getEmployeesAndDept() {
        List<Employee> employees = new ArrayList<>();
//        String hql="FROM Employee as e LEFT JOIN FETCH e.department ";
        String hql="FROM Employee  ";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Employee> query=session.createQuery(hql);
            employees=query.list();
            session.close();
            return employees;
        }
        catch (Exception e){
            logger.error("failure to retrieve data record",e);
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Employee getEmployeeByName(String employeeName) {
        Transaction transaction=null;
        if (employeeName==null) return null;
        String hql="FROM Employee as em where lower(em.name)=:name";
        //String hql="FROM Employee as em left join fetch em.account where em.name=:name";
        Session session=HibernateUtil.getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query<Employee> query=session.createQuery(hql);
        query.setParameter("name",employeeName.toLowerCase());
        Employee employee=query.uniqueResult();
        transaction.commit();
        session.close();
        return employee;

    }
}
