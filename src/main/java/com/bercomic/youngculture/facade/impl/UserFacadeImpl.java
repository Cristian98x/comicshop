package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.UserConverter;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.facade.UserFacade;
import com.bercomic.youngculture.service.UserService;
import com.bercomic.youngculture.service.impl.UserServiceImpl;

public class UserFacadeImpl implements UserFacade {
    private UserService userService = new UserServiceImpl();
    private UserConverter userConverter = new UserConverter();

    @Override
    public void save(UserDTO userDto) {
        userService.save(userConverter.dtoToEntity(userDto));
    }

    @Override
    public UserDTO login(String email, String password) {
        return userConverter.entityToDto(userService.login(email, password));
    }

    @Override
    public boolean isEmail(String email){
        if(userService.isEmail(email)==true){
            return true;
        }else
        {
            return false;
        }
    }
}
