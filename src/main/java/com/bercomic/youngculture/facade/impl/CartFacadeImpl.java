package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.CartConverter;
import com.bercomic.youngculture.converter.ProductConverter;
import com.bercomic.youngculture.converter.UserConverter;
import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.model.User;
import com.bercomic.youngculture.service.CartService;
import com.bercomic.youngculture.service.impl.CartServiceImpl;

public class CartFacadeImpl implements CartFacade {

    private CartService cartService = new CartServiceImpl();
    private CartConverter cartConverter = new CartConverter();
    private ProductConverter productConverter = new ProductConverter();
    private UserConverter userConverter = new UserConverter();

    @Override
    public void save(CartDTO cartDTO) {
        cartService.save(cartConverter.dtoToEntity(cartDTO));
    }

    @Override
    public CartDTO getByUserId(Long idUser)  {
      CartDTO cartDTO;
        try {
            cartDTO = cartConverter.entityToDto(cartService.getByUserId(idUser));
        } catch (CartException cartException) {
            cartDTO = null;
        }
      return cartDTO;
    }

    @Override
    public void update(CartDTO cartDTO) {
        cartService.update(cartConverter.dtoToEntity(cartDTO));
    }

    @Override
    public void remove(CartDTO cartDTO) {
        cartService.remove(cartConverter.dtoToEntity(cartDTO));
    }

    @Override
    public void changeQuantity(UserDTO userDTO, Long cartItemToModifyQuantity, Integer newQuantity, CartDTO cartFromSession) {
        cartService.changeQuantity(userDTO, cartItemToModifyQuantity, newQuantity, cartFromSession);
    }

    @Override
    public void deleteFromCart(UserDTO loggedUser, Long cartItemToDelete, CartDTO cartFromSession) {
        cartService.deleteFromCart(loggedUser, cartItemToDelete, cartFromSession);
    }

    @Override
    public void addToCartWhenUserIsLogged(CartDTO cartDTO, ProductDTO newProductToAdd, UserDTO userLoggedDTO) {
        if(userLoggedDTO!=null){
            Product product = productConverter.dtoToEntity(newProductToAdd);
            User user = userConverter.dtoToEntity(userLoggedDTO);
            cartService.addToCartWhenUserIsLogged(cartDTO, product, user);
        }
    }

    @Override
    public CartDTO merge(UserDTO userDTO, CartDTO cartFromSession) {
        return cartConverter.entityToDto(cartService.merge(userDTO, cartFromSession));
    }


}
