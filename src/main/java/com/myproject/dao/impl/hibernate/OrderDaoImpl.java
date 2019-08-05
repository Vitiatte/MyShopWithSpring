package com.myproject.dao.impl.hibernate;

import com.myproject.dao.OrderDao;
import com.myproject.entity.Order;
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
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Optional<Long> add(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            id = order.getId();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when order was being added", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(id);
    }

    @Override
    @Transactional
    public Optional<Order> getOrderById(Long orderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Order order = null;

        try {
            transaction = session.beginTransaction();
            order = session.get(Order.class, orderId);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when order was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when order was being updated", e);
        } finally {
            session.close();
        }
    }
}
