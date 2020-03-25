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
//import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private DepartmentDao departmentDao=new DepartmentDaoImpl();
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    //pass
    public boolean save(Employee employee, String deptName) {
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
                    return true;
                }else{
                    logger.error("the department name does not exist");
                    return false;
                }
        }
        catch (Exception exception){
            if(transaction!=null) transaction.rollback();
            logger.error(exception.getMessage());
            session.close();
            return false;
        }
    }

    @Override
    public int updateEmployeeAddress(String name, String address) {
        return 0;
    }

    @Override
    //PASS
    public boolean delete(Employee e) {
        String hql="DELETE Employee as emp where emp.id=:Id";
        int deletedCount;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Employee> query=session.createQuery(hql);
            query.setParameter("Id",e.getId());
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
    public List<Employee> getEmployees() {
        List<Employee> emps=new ArrayList<>();
        String hql="FROM Employee em left join fetch em.account";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Employee> query=session.createQuery(hql);
            //new feature in JAVA8
            return query.list().stream().distinct().collect(Collectors.toList());
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
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Employee as em left join fetch em.account where em.name=:name";
        try {
            Query<Employee> query=session.createQuery(hql);
            query.setParameter("name",employeeName);
            //Convenience method to return a single instance that matches the query, or null if the query returns no results.
            return query.uniqueResult();
        }
        catch (Exception e){
            logger.error("failure to retrieve employee by name");
            return null;
        }finally {
            session.close();
        }
    }
}
