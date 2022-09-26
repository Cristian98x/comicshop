package com.bercomic.youngculture.dao;

import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.utils.SessionManager;

import java.util.List;

public interface ProductDAO extends SessionManager {
    void save(Product product);
   List<Product> findAll();
   List<Product> findByCategory(String category);
   Product findById(Long id);
}
