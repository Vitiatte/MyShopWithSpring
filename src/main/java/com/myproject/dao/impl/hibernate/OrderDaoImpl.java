package com.myproject.dao.impl.hibernate;

import com.myproject.dao.OrderDao;
import com.myproject.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Long> add(Order order) {
        sessionFactory.getCurrentSession().save(order);
        Long id = order.getId();
        return Optional.ofNullable(id);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        return Optional.ofNullable(order);
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }
}
