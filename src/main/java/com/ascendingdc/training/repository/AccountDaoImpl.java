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
//import org.springframework.stereotype.Repository;

//@Repository
public class AccountDaoImpl implements AccountDao{
    private Logger logger= LoggerFactory.getLogger(getClass());
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
            logger.error("failure to insert record into Employee table");
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Account account) {
        String hql="DELETE account as account where account.id=:Id";
        int deletedCount=0;
        Transaction transaction=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query<Employee> query=session.createQuery(hql);
            query.setParameter("Id",account.getId());
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


}
