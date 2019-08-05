package com.myproject.service.impl;

import com.myproject.dao.ProductDao;
import com.myproject.entity.Product;
import com.myproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public void add(Product product) {
        productDao.add(product);
    }

    public Optional<Product> getProductById(Long id) {
        if (id > 0) {
            return productDao.getProductById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
