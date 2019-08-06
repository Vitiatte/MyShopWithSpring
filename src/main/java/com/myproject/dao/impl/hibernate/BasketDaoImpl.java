package com.myproject.dao.impl.hibernate;

import com.myproject.dao.BasketDao;
import com.myproject.entity.Basket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BasketDaoImpl implements BasketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Basket basket) {
        sessionFactory.getCurrentSession().save(basket);
    }

    @Override
    public Optional<Basket> getBasketForUser(long userId) {
        Session session = sessionFactory.getCurrentSession();
        Basket basket = (Basket) session.createQuery("FROM Basket WHERE userId = :userId")
                    .setParameter("userId", userId)
                    .uniqueResult();
        return Optional.ofNullable(basket);
    }

    @Override
    public void updateBasketsProductList(Basket basket) {
        sessionFactory.getCurrentSession().saveOrUpdate(basket);
    }
}
