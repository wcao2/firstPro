package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Department;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//DepartmentDaoImpl class generates instance will be viewed as dependency by Spring container(box)
//when Spring lanuch, DepartmentDao dd=new DepartmentDaoImpl();
@Repository
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

    //1 getDepartmentById
    @Override
    public Department getDepartmentById(Long id) {
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

    //2: getDeptByName
    @Override
    public Department getDepartmentByName(String deptName) {
        Transaction transaction=null;
        if(deptName==null) return null;
        String hql="FROM Department as dept " +
                "where lower(dept.name)=:name";
        Session session=HibernateUtil.getSessionFactory().openSession();
        transaction=session.beginTransaction();
        Query<Department> query=session.createQuery(hql);
        query.setParameter("name",deptName.toLowerCase());
        Department department=query.uniqueResult();
        transaction.commit();
        session.close();
        return department;
    }

    //3
    @Override
    public List<Department> getDepartmentsLazy() {
        String hql = "FROM Department";
        List<Department> deps=new ArrayList<>();
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            deps= query.list();
            session.close();
            return deps;
//            return query.list().stream().distinct().collect(Collectors.toList());
            //return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }catch (Exception e){
            logger.error("failure to retrieve departments and relevant emeployees",e);
            session.close();
            return null;
        }
    }

    //4
    public List<Department> getDepartmentsEager() {
//        String hql="SELECT distinct dept FROM Department as dept left join fetch dept.employee as em left join fetch em.account";
        String hql="FROM Department d LEFT JOIN FETCH d.employee";
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Department> query=session.createQuery(hql);
            //remove duplicate
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
    public boolean delete(Long id) {
        String hql="DELETE Department as dep where dep.id=:Id";
        int deletedCount=0;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Department> query=session.createQuery(hql);
            query.setParameter("Id",id);
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


    @Override
    public boolean update(Department dep) {
        Transaction transaction=null;
        boolean isSuccess=true;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            session.saveOrUpdate(dep);
            transaction.commit();
            session.close();
        }
        catch (Exception e){
            isSuccess=false;
            if(transaction!=null) transaction.rollback();
            session.close();
            logger.error("Failure to update record",e.getMessage());
        }
        return isSuccess;
    }


}














