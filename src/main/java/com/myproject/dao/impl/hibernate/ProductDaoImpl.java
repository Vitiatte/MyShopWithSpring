package com.myproject.dao.impl.hibernate;

import com.myproject.dao.ProductDao;
import com.myproject.entity.Product;
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
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when product was being added", e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional
    public Optional<Product> getProductById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Product product = null;

        try {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when product was being gotten", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(product);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        List<Product> productList = null;

        try {
            transaction = session.beginTransaction();
            productList = session.createQuery("FROM Product").list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when products were being gotten", e);
        } finally {
            session.close();
        }
        return (Objects.nonNull(productList) && !productList.isEmpty()) ? productList :
                Collections.emptyList();
    }

    @Override
    @Transactional
    public void update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when product was being updated", e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional
    public void delete(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception when product was being deleted", e);
        } finally {
            session.close();
        }
    }
}
