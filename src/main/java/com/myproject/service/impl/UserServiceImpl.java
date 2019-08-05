package com.myproject.service.impl;

import com.myproject.dao.BasketDao;
import com.myproject.dao.UserDao;
import com.myproject.entity.Basket;
import com.myproject.entity.User;
import com.myproject.entity.enums.UserRole;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BasketDao basketDao;

    public void add(User user) {
        long userId = userDao.add(user).get();
        if (user.getUserRole().equals(UserRole.USER)) {
            Basket basket = new Basket();
            basket.setUserId(userId);
            basketDao.add(basket);
        }
    }

    public Optional<User> getUserById(Long id) {
        return id > 0 ? userDao.getUserById(id) : Optional.empty();
    }

    public Optional<User> getUserByLoginAndPass(String login, String pass) {
        return userDao.getUserByLoginAndPass(login, pass);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
