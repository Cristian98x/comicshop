package com.bercomic.youngculture.service;

import com.bercomic.youngculture.model.User;

public interface UserService {

    void save(User user);
    User login(String email,String password);
    boolean isEmail(String email) ;
}
