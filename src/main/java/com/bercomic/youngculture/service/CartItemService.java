package com.bercomic.youngculture.service;

import com.bercomic.youngculture.model.CartItem;

public interface CartItemService {

    void save(CartItem cartItem);
    void remove(CartItem cartItem);
    void update(CartItem cartItem);
}
