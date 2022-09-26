package com.bercomic.youngculture.service;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;
import com.bercomic.youngculture.model.Cart;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.model.User;

public interface CartService {

    void save(Cart cart);
    Cart getByUserId(Long userId) throws CartException;
    void update(Cart cart);
    void remove(Cart cart);
    CartDTO addToCart(CartDTO cartDTO, boolean isItemInCart, ProductDTO productDTO);
    void changeQuantity(UserDTO loggedUser, Long cartItemToModifyQuantity, Integer newQuantity, CartDTO cartFromSession);
    void deleteFromCart(UserDTO loggedUser, Long cartItemToDelete, CartDTO cartFromSession);
    void addToCartWhenUserIsLogged(CartDTO cart, Product newProductToAdd, User userLogged);
    Cart merge( UserDTO user,CartDTO cartFromSession);

}

