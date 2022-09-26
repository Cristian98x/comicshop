package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.constants.UserConstant;
import com.bercomic.youngculture.dao.UserDAO;
import com.bercomic.youngculture.model.User;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;


public class UserDaoImpl extends SessionManagerImpl implements UserDAO {

    @Override
    public void save(User user) {
        this.currentSession.save(user);
    }

    @Override
    public User login(String email, String password) {
        Query query = this.currentSession.createQuery("from User u where u.email=:email and u.password=:password");
        query.setParameter(UserConstant.EMAIL, email);
        query.setParameter(UserConstant.PASSWORD, password);
        return (User) query.getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        try {
            Query query = this.currentSession.createQuery("from User u where u.email=:email");
            query.setParameter(UserConstant.EMAIL, email);
            return (User) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }

    }
}
