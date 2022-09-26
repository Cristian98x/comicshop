package com.bercomic.youngculture.dao;

import com.bercomic.youngculture.model.CartItem;
import com.bercomic.youngculture.utils.SessionManager;

public interface CartItemDAO extends SessionManager {
    void save(CartItem cartItem);
    void remove(CartItem cartItem);
    void update(CartItem cartItem);
}
