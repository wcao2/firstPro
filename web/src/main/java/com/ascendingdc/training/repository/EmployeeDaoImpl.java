package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

//    @Autowired
//    SessionFactory sessionFactory;

    @Override
    //pass
    public Employee save(Employee employee, String deptName) {
        Transaction transaction=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
                Department department=departmentDao.getDepartmentByName(deptName);
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
    public Employee updateEmployeeEmail(Employee e) {
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            session.update(e);
            transaction.commit();
            session.close();
            return e;
        }catch (Exception exception){
            if(transaction!=null) transaction.rollback();
            logger.error(exception.getMessage());
            session.close();
            return null;
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
            return false;
        }
    }

    //pass   1
    public List<Employee> getEmployeesAndDept() {
        List<Employee> employees = new ArrayList<>();
        String hql="FROM Employee as e LEFT JOIN FETCH e.department";
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
    public Employee getEmployeeById(Long Id) {
        if(Id==null) return null;
        String hql="FROM Employee as em LEFT JOIN FETCH em.department where em.id=:id";
        Session session=HibernateUtil.getSessionFactory().openSession();

        Query<Employee> query=session.createQuery(hql);
        query.setParameter("id",Id);
        Employee employee=query.uniqueResult();
        session.close();
        return employee;
    }

    @Override  //2
    public Employee getEmployeeByName(String employeeName) {
        Transaction transaction=null;
        if (employeeName==null) return null;
        String hql="FROM Employee as em LEFT JOIN FETCH em.department where lower(em.name)=:name";
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


    @Override
    public Employee getEmployeeByCredentials(String email, String password) throws Exception {
        String hql = "FROM Employee as e left join fetch e.roles where (lower(e.email) = :email or lower(e.name) =:email) and e.password = :password";
        //logger.debug(String.format("User email: %s, password: %s", email, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }
        catch (Exception e){
            throw new Exception("can't find user record or session");
        }
    }
}




















