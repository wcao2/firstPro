package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao{
    private Logger logger= LoggerFactory.getLogger(getClass());

    public Department save(Department department) {
        Transaction transaction=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
            transaction=session.beginTransaction();
            session.save(department);
            transaction.commit();
            session.close();
            return department;
        }
        catch (Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error("failure to insert record");
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Department dep) {
        String hql="DELETE Department as dep where dep.id=:Id";
        int deletedCount=0;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Department> query=session.createQuery(hql);
            query.setParameter("Id",dep.getId());
            deletedCount=query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount>=1?true:false;
        }
        catch (HibernateException e){
            if(transaction!=null) transaction.rollback();
            session.close();
            logger.error("unable to delete record",e);
        }
        return false;
    }

    public List<Department> getDepartments() {
        List<Department> deps=new ArrayList<>();//Diamond types are not supported at language level '5' if remove <Department>
        String hql="FROM Department";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Department> query=session.createQuery(hql);
            deps= query.list();
            session.close();
            return deps;
        }
        catch (Exception e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return deps;
        }
    }

    @Override
    public List<Department> getDepartmentWithChildren() {


        return null;
    }

    @Override
    public Department getDepartmentEagerBy(Long id) {
        String hql="FROM Department d LEFT JOIN FETCH d.employee where d.id=:Id";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Department> query=session.createQuery(hql);
            query.setParameter("Id",id);
            Department result= query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }

    }

    @Override
    public Department getDepartmentLazyBy(Long id) {
        String hql="FROM Department d where d.id=:Id";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Department> query=session.createQuery(hql);
            query.setParameter("Id",id);
            Department result=query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }
}
