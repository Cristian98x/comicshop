package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.UserDTO;

public interface UserFacade {

    void save(UserDTO userDto);
    UserDTO login(String email, String password);
    boolean isEmail(String email);
}
