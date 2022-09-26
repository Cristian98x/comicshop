package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.dao.UserDAO;
import com.bercomic.youngculture.dao.impl.UserDaoImpl;
import com.bercomic.youngculture.model.User;
import com.bercomic.youngculture.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDaoImpl();
    }

    @Override
    public void save(User user) {
        userDAO.openCurrentSession();
        userDAO.save(user);
        userDAO.closeCurrentSession();
    }

    @Override
    public User login(String email, String password) {
        userDAO.openCurrentSession();
        User user = userDAO.login(email, password);
        userDAO.closeCurrentSession();
        return user;
    }

    @Override
    public boolean isEmail(String email) {

        userDAO.openCurrentSession();
        User user = userDAO.findByEmail(email);
        userDAO.closeCurrentSession();
        if (user == null) {
            return false;
        }else
        {
            return true;
        }

    }
}
