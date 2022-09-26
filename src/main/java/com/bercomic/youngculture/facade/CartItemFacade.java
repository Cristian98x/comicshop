package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.CartItemDTO;

public interface CartItemFacade {

    void save(CartItemDTO cartItemDTO);
    void update(CartItemDTO cartItemDTO);
    void remove(CartItemDTO cartItemDTO);
}
