package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.dao.CartItemDAO;
import com.bercomic.youngculture.dao.impl.CartItemDaoImpl;
import com.bercomic.youngculture.model.CartItem;
import com.bercomic.youngculture.service.CartItemService;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    public CartItemServiceImpl(){
        cartItemDAO = new CartItemDaoImpl();
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemDAO.openCurrentSession();
        cartItemDAO.save(cartItem);
        cartItemDAO.closeCurrentSession();
    }

    @Override
    public void remove(CartItem cartItem) {
        cartItemDAO.openCurrentSessionWithTransaction();
        cartItemDAO.remove(cartItem);
        cartItemDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(CartItem cartItem) {
        cartItemDAO.openCurrentSessionWithTransaction();
        cartItemDAO.update(cartItem);
        cartItemDAO.closeCurrentSessionWithTransaction();
    }
}
