package com.myproject.service;

import com.myproject.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void add(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    void update(Product product);

    void delete(Product product);
}
