package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.model.Cart;
import com.bercomic.youngculture.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartConverter {

    private UserConverter userConverter = new UserConverter();
    private CartItemConverter cartItemConverter = new CartItemConverter();

    public Cart dtoToEntity (CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUser(userConverter.dtoToEntity(cartDTO.getUserDTO()));
        List<CartItem> cartItems = new ArrayList<>();
        List<CartItemDTO> cartItemDTOS = cartDTO.getCartItemDtos();
        for(CartItemDTO cartItemDTO1:cartItemDTOS){
            CartItem cartItem = cartItemConverter.dtoToEntity(cartItemDTO1);
            cartItems.add(cartItem);
        }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(cartDTO.getTotalPrice());

        return cart;
    }

    public CartDTO entityToDto(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserDTO(userConverter.entityToDto(cart.getUser()));
        cartDTO.setTotalPrice(cart.getTotalPrice());
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        List<CartItem> cartItems = cart.getCartItems();
        for(CartItem cartItem:cartItems){
            CartItemDTO cartItemDTO = cartItemConverter.entityToDto(cartItem);
            cartItemDTOS.add(cartItemDTO);
        }
        cartDTO.setCartItemDtos(cartItemDTOS);
        return cartDTO;
    }


}
