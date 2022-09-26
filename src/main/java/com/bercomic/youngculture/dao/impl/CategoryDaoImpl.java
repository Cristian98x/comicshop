package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.constants.QueryConstant;
import com.bercomic.youngculture.dao.CategoryDAO;
import com.bercomic.youngculture.model.Category;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class CategoryDaoImpl extends SessionManagerImpl implements CategoryDAO {

    @Override
    public void save(Category category) {
        this.currentSession.save(category);
    }

    @Override
    public List<Category> findAll() {
        Query query = this.currentSession.createQuery(QueryConstant.ALL_CATEGORY);
        return (List<Category>) query.getResultList();
    }

    @Override
    public Category findByName(String name) {
        try {
            Query query = this.currentSession.createQuery(QueryConstant.GET_CATEGORY_BY_NAME);
            query.setParameter("name", name);
            return (Category) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
