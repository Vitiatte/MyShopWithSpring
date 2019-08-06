package com.myproject.service.impl;

import com.myproject.dao.ProductDao;
import com.myproject.entity.Product;
import com.myproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Transactional
    @Override
    public Optional<Product> getProductById(Long id) {
        if (id > 0) {
            return productDao.getProductById(id);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Transactional
    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
