package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;

public interface CartFacade {

    void save(CartDTO cartDTO);
    CartDTO getByUserId(Long idUser) throws CartException;
    void update(CartDTO cartDTO);
    void remove(CartDTO cartDTO);
    void changeQuantity(UserDTO userDTO, Long cartItemToModifyQuantity, Integer newQuantity, CartDTO cartFromSession);
    void deleteFromCart(UserDTO loggedUser, Long cartItemToDelete, CartDTO cartFromSession);
    void addToCartWhenUserIsLogged(CartDTO cartDTO, ProductDTO newProductToAdd, UserDTO userLoggedDTO);
    CartDTO merge( UserDTO userDTO,CartDTO cartFromSession);


}
