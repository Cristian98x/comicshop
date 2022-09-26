package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.CartItemConverter;
import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.facade.CartItemFacade;
import com.bercomic.youngculture.service.CartItemService;
import com.bercomic.youngculture.service.impl.CartItemServiceImpl;

public class CartItemFacadeImpl implements CartItemFacade {
    private CartItemService  cartItemService = new CartItemServiceImpl();
    private CartItemConverter cartItemConverter = new CartItemConverter();


    @Override
    public void save(CartItemDTO cartItemDTO) {
        cartItemService.save(cartItemConverter.dtoToEntity(cartItemDTO));
    }

    @Override
    public void update(CartItemDTO cartItemDTO) {
        cartItemService.update(cartItemConverter.dtoToEntity(cartItemDTO));
    }

    @Override
    public void remove(CartItemDTO cartItemDTO) {
        cartItemService.remove(cartItemConverter.dtoToEntity(cartItemDTO));
    }
}
