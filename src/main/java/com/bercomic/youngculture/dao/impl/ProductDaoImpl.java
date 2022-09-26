package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.dao.ProductDAO;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;
import org.hibernate.query.Query;
import com.bercomic.youngculture.constants.QueryConstant;


import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends SessionManagerImpl implements ProductDAO {

    @Override
    public void save(Product product) {
        this.currentSession.save(product);
    }

    @Override
    public List<Product> findAll() {
        Query query = this.currentSession.createQuery(QueryConstant.ALL_PRODUCTS);
        return (List<Product>) query.getResultList();

    }

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> products;
        if(category.equals("All")){
            products=findAll();
        }else{
            Query query =  this.currentSession.createQuery(QueryConstant.PRODUCT_BY_CATEGORY);
            query.setParameter("name",category);
             products=(List<Product>)query.getResultList();
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Query query =  this.currentSession.createQuery(QueryConstant.PRODUCT_BY_ID);
        query.setParameter("id",id);
        return (Product)query.getSingleResult();
    }


}
