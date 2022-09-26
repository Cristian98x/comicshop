package com.bercomic.youngculture.service;

import com.bercomic.youngculture.model.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    List<Product> findAll();
    List<Product> getByCategory(String category);
    Product getById(Long id);
}
