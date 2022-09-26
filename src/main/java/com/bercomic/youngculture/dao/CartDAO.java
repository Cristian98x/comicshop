package com.bercomic.youngculture.dao;


import com.bercomic.youngculture.model.Cart;
import com.bercomic.youngculture.utils.SessionManager;

public interface CartDAO extends SessionManager {
    void save(Cart cart);
    Cart findByUserId(Long idUser);
    void update(Cart cart);
    void remove(Cart cart);
}
