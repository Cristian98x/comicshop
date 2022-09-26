package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.dao.CartItemDAO;
import com.bercomic.youngculture.model.CartItem;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;

public class CartItemDaoImpl extends SessionManagerImpl implements CartItemDAO {


    @Override
    public void save(CartItem cartItem) {
        this.currentSession.save(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
        this.currentSession.remove(cartItem);
    }

    @Override
    public void update(CartItem cartItem) {
        this.currentSession.update(cartItem);
    }


}
