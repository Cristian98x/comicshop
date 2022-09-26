package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.dao.CategoryDAO;
import com.bercomic.youngculture.dao.impl.CategoryDaoImpl;
import com.bercomic.youngculture.model.Category;
import com.bercomic.youngculture.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryServiceImpl() {
        categoryDAO = new CategoryDaoImpl();
    }

    @Override
    public void save(Category category) {
        categoryDAO.openCurrentSession();
        categoryDAO.save(category);
        categoryDAO.closeCurrentSession();
    }

    @Override
    public List<Category> findAll() {
        categoryDAO.openCurrentSessionWithTransaction();
        List<Category> categoryList = categoryDAO.findAll();
        categoryDAO.closeCurrentSessionWithTransaction();
        return categoryList;
    }

    @Override
    public Category getByName(String name) {
        categoryDAO.openCurrentSession();
        Category category = categoryDAO.findByName(name);
        categoryDAO.closeCurrentSession();
        return category;
    }
}
