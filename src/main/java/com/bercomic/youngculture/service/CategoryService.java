package com.bercomic.youngculture.service;

import com.bercomic.youngculture.model.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);
    List<Category> findAll();
    Category getByName(String name);
}
