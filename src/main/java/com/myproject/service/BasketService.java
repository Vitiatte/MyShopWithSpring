package com.myproject.service;

import com.myproject.entity.Basket;

import java.util.Optional;

public interface BasketService {

    void add(Basket basket);

    Optional<Basket> getBasketForUser(long userID);

    void update(Basket basket);
}
