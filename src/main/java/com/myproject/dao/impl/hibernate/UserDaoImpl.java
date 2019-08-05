package com.myproject.dao.impl.hibernate;

import com.myproject.dao.UserDao;
import com.myproject.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Optional<Long> add(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Long id = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            id = user.getId();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being added", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(id);
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        User user = null;

        try {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<User> getUserByLoginAndPass(String login, String pass) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        User user = null;

        try {
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE login=:login AND " +
                    "password=:password")
                    .setParameter("login", login)
                    .setParameter("password", pass)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<User> getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        User user = null;

        try {
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE login=:login")
                    .setParameter("login", login)
                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        List<User> users = null;

        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when users were being gotten", e);
        } finally {
            session.close();
        }
        return (Objects.nonNull(users) && !users.isEmpty()) ? users : Collections.emptyList();
    }

    @Override
    @Transactional
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being updated", e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when user was being deleted", e);
        } finally {
            session.close();
        }
    }
}

