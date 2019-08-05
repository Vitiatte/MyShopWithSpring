package com.myproject.dao.impl.hibernate;

import com.myproject.dao.BasketDao;
import com.myproject.entity.Basket;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Basket basket) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when basket was being added", e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional
    public Optional<Basket> getBasketForUser(long userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Basket basket = null;

        try {
            transaction = session.beginTransaction();
            basket = (Basket) session.createQuery("FROM Basket WHERE userId=:userId")
                    .setParameter("userId", userId)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when basket was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(basket);
    }

    @Override
    @Transactional
    public void updateBasketsProductList(Basket basket) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(basket);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when basket was being updated", e);
        } finally {
            session.close();
        }
    }
}
