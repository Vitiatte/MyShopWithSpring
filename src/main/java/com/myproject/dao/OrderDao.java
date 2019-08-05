package com.myproject.dao;

import com.myproject.entity.Order;

import java.util.Optional;

public interface OrderDao {

    Optional<Long> add(Order order);

    Optional<Order> getOrderById(Long orderId);

    void update(Order order);
}
