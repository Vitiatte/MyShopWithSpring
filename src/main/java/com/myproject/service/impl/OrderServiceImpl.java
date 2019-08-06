package com.myproject.service.impl;

import com.myproject.dao.OrderDao;
import com.myproject.entity.Order;
import com.myproject.exception.InvalidOrderIdException;
import com.myproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public long add(Order order) {
        Optional<Long> optional = orderDao.add(order);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new InvalidOrderIdException("Order id didn't return from DB");
        }
    }

    @Transactional
    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Transactional
    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
