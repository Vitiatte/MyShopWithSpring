package com.myproject.dao.impl.hibernate;

import com.myproject.dao.UserDao;
import com.myproject.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Long> add(User user) {
        sessionFactory.getCurrentSession().save(user);
        Long id = user.getId();
        return Optional.ofNullable(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByLoginAndPass(String login, String pass) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User WHERE login = :login AND " +
                    "password = :password")
                    .setParameter("login", login)
                    .setParameter("password", pass)
                    .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User WHERE login = :login")
                    .setParameter("login", login)
                    .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("FROM User").list();
        return (Objects.nonNull(users) && !users.isEmpty())
                ? users
                : Collections.emptyList();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}

