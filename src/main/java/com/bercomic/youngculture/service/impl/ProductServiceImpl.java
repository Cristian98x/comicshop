package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.dao.ProductDAO;
import com.bercomic.youngculture.dao.impl.ProductDaoImpl;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    public ProductServiceImpl(){
        productDAO = new ProductDaoImpl();
    }

    @Override
    public void save(Product product){
        productDAO.openCurrentSession();
        productDAO.save(product);
        productDAO.closeCurrentSession();
    }

    @Override
    public List<Product> findAll() {
        productDAO.openCurrentSession();
        List<Product> productList = productDAO.findAll();
        productDAO.closeCurrentSession();
        return productList;
    }

    @Override
    public List<Product> getByCategory(String category) {
        productDAO.openCurrentSession();
        List<Product> products = productDAO.findByCategory(category);
        productDAO.closeCurrentSession();
        return products;
    }

    @Override
    public Product getById(Long id) {
        productDAO.openCurrentSession();
        Product product = productDAO.findById(id);
        productDAO.closeCurrentSession();
        return product;
    }

}
