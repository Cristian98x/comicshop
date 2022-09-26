package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.model.CartItem;


public class CartItemConverter {

    private ProductConverter productConverter = new ProductConverter();

    public CartItem dtoToEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setProduct(productConverter.dtoToEntity(cartItemDTO.getProductDTO()));
        cartItem.setQuantity(cartItemDTO.getQuantity());
        return cartItem;
    }

    public CartItemDTO entityToDto(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setProductDTO(productConverter.entityToDto(cartItem.getProduct()));
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }
}
