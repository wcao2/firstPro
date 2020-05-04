package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Image;
import com.ascendingdc.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public Image save(Image image) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(image);
            transaction.commit();
            session.close();
            return image;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save this file",e);
            return null;
        }
    }

    @Override
    public int delByUserId(Long id) {
        String hql="delete from Image where employee.id=:id";
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            int num=query.executeUpdate();
            transaction.commit();
            session.close();
            return num;
        }catch (Exception e){
            session.close();
            logger.error("Faled to delete this record ",e);
            return -1;
        }
    }

    @Override
    public List<Image> getByEmployeeId(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="from Image as i where employee.id=:id";
        Query query=session.createQuery(hql);
        query.setParameter("id",id);
        List<Image> images=query.list();
        session.close();
        return images;
    }
}





















