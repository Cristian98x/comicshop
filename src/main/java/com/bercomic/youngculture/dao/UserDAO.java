package com.bercomic.youngculture.dao;

import com.bercomic.youngculture.model.User;
import com.bercomic.youngculture.utils.SessionManager;

public interface UserDAO extends SessionManager {

    void save(User user);
    User login(String email,String password);
    User findByEmail(String email);

}
