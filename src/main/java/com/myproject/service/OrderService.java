package com.myproject.service;

import com.myproject.entity.Order;

import java.util.Optional;

public interface OrderService {

    long add(Order order);

    Optional<Order> getOrderById(Long orderId);

    void update(Order order);
}
