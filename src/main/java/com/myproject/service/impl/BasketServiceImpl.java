package com.myproject.service.impl;

import com.myproject.dao.BasketDao;
import com.myproject.entity.Basket;
import com.myproject.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketDao basketDao;

    @Override
    public void add(Basket basket) {
        basketDao.add(basket);
    }

    @Override
    public Optional<Basket> getBasketForUser(long userID) {
        return basketDao.getBasketForUser(userID);
    }

    @Override
    public void update(Basket basket) {
        basketDao.updateBasketsProductList(basket);
    }
}
