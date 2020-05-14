package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class AccountDaoImpl implements AccountDao{
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public Account getAccountById(Long Id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Account as a left join fetch a.employee where a.id=:id";
        try {
            Query<Account> query=session.createQuery(hql);
            query.setParameter("id",Id);
            //Convenience method to return a single instance that matches the query, or null if the query returns no results.
            return query.uniqueResult();
        }
        catch (Exception e){
            logger.error("failure to retrieve Account details");
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Account save(Account account) {
        Transaction transaction=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
            transaction=session.beginTransaction();
            session.save(account);
            transaction.commit();
            session.close();
            return account;
        }catch (Exception exception){
            if(transaction!=null) transaction.rollback();
            logger.error("failure to insert record into Account table");
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        String hql="DELETE Account as account where account.id=:Id";
        int deletedCount=0;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Account> query=session.createQuery(hql);
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
}















