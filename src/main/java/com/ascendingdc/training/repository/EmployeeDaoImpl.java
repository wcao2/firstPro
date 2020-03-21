package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDaoImpl implements EmployeeDao {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public Employee save(Employee e) {
        Transaction transaction=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
            transaction=session.beginTransaction();
            session.save(e);
            transaction.commit();
            session.close();
            return e;
        }catch (Exception exception){
            if(transaction!=null) transaction.rollback();
            logger.error("failure to insert record into Employee table");
            session.close();
            return null;
        }

    }
}
