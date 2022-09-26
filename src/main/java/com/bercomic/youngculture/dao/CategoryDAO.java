package com.bercomic.youngculture.dao;

import com.bercomic.youngculture.model.Category;
import com.bercomic.youngculture.utils.SessionManager;

import java.util.List;

public interface CategoryDAO extends SessionManager {

    void save(Category category);
    List<Category> findAll();
    Category findByName (String name);
}
