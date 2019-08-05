package com.myproject.dao;

import com.myproject.entity.Basket;

import java.util.Optional;

public interface BasketDao {

    void add(Basket basket);

    Optional<Basket> getBasketForUser(long userID);

    void updateBasketsProductList(Basket basket);
}
