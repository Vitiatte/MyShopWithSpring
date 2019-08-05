package com.myproject.service.impl;

import com.myproject.dao.OrderDao;
import com.myproject.entity.Order;
import com.myproject.exception.InvalidOrderIdException;
import com.myproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public long add(Order order) {
        Optional<Long> optional = orderDao.add(order);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new InvalidOrderIdException("Order id didn't return from DB");
        }
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
