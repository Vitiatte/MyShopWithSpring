package com.myproject.service.impl;

import com.myproject.dao.BasketDao;
import com.myproject.dao.UserDao;
import com.myproject.entity.Basket;
import com.myproject.entity.User;
import com.myproject.entity.enums.UserRole;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BasketDao basketDao;

    @Transactional
    @Override
    public void add(User user) {
        long userId = userDao.add(user).get();
        if (user.getUserRole().equals("USER_ROLE")) {
            Basket basket = new Basket();
            basket.setUserId(userId);
            basketDao.add(basket);
        }
    }

    @Transactional
    @Override
    public Optional<User> getUserById(Long id) {
        return id > 0 ? userDao.getUserById(id) : Optional.empty();
    }

    @Transactional
    @Override
    public Optional<User> getUserByLoginAndPass(String login, String pass) {
        return userDao.getUserByLoginAndPass(login, pass);
    }

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
