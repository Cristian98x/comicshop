package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.converter.CartConverter;
import com.bercomic.youngculture.converter.UserConverter;
import com.bercomic.youngculture.dao.CartDAO;
import com.bercomic.youngculture.dao.impl.CartDaoImpl;
import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;
import com.bercomic.youngculture.model.Cart;
import com.bercomic.youngculture.model.CartItem;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.model.User;
import com.bercomic.youngculture.service.CartService;

import javax.persistence.NoResultException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;
    private CartConverter cartConverter = new CartConverter();
    private UserConverter userConverter = new UserConverter();

    public CartServiceImpl() {
        cartDAO = new CartDaoImpl();
    }

    @Override
    public void save(Cart cart) {
        cartDAO.openCurrentSession();
        cartDAO.save(cart);
        cartDAO.closeCurrentSession();
    }

    @Override
    public Cart getByUserId(Long userId) throws CartException {
        try {
            cartDAO.openCurrentSession();
            Cart cart = cartDAO.findByUserId(userId);
            cartDAO.closeCurrentSession();
            return cart;
        } catch (NoResultException noResultException) {
            throw new CartException("User don't have a cart");
        }
    }

    @Override
    public void update(Cart cart) {
        cartDAO.openCurrentSessionWithTransaction();
        cartDAO.update(cart);
        cartDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void remove(Cart cart) {
        cartDAO.openCurrentSessionWithTransaction();
        cartDAO.remove(cart);
        cartDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public CartDTO addToCart(CartDTO cartDTO, boolean isItemInCart, ProductDTO productDTO) {
        List<CartItemDTO> cartItemDTOS;
        if (cartDTO == null) {
            cartDTO = createCartAfterFirstItemIsAdded(productDTO);
        } else {
            cartItemDTOS = cartDTO.getCartItemDtos();
            CartItemDTO itemInCart = new CartItemDTO();
            for (CartItemDTO cartItemDTO : cartItemDTOS) {
                if (isProductInCartItem(productDTO, cartItemDTO)) {
                    itemInCart = cartItemDTO;
                    isItemInCart = true;
                }
            }
            updateCart(cartDTO, isItemInCart, productDTO, cartItemDTOS, itemInCart);
        }
        return cartDTO;
    }

    @Override
    public void changeQuantity(UserDTO loggedUser, Long cartItemToModifyQuantity, Integer newQuantity, CartDTO cartFromSession) {
        {
            if (loggedUser != null) {
                try {
                    Cart cartFromDB = getByUserId(loggedUser.getId());
                    List<CartItem> itemFromCart = cartFromDB.getCartItems();
                    for (CartItem cartItem : itemFromCart) {
                        if (cartItem.getProduct().getId().equals(cartItemToModifyQuantity)) {
                            setQuantityAndTotalPrice(newQuantity, cartFromDB, cartItem);
                            break;
                        }
                    }
                } catch (CartException e) {
                    e.printStackTrace();
                }
            } else {

                List<CartItemDTO> itemFromCart = cartFromSession.getCartItemDtos();
                for (CartItemDTO cartItem : itemFromCart) {
                    if (cartItem.getProductDTO().getId().equals(cartItemToModifyQuantity)) {
                        setQuantityAndTotalPrice(newQuantity, cartFromSession, cartItem);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void deleteFromCart(UserDTO loggedUser, Long cartItemToDelete, CartDTO cartFromSession) {
        if (loggedUser != null) {
            try {
                Cart cartFromDB = getByUserId(loggedUser.getId());
                List<CartItem> itemFromCart = cartFromDB.getCartItems();
                for (CartItem cartItem : itemFromCart) {
                    if (cartItem.getProduct().getId().equals(cartItemToDelete)) {
                        itemFromCart.remove(cartItem);
                        calculateTotalPriceAfterDelete(cartFromDB, cartItem);
                        update(cartFromDB);
                        break;
                    }
                }
            } catch (CartException e) {
                e.printStackTrace();
            }
        } else {
            List<CartItemDTO> itemFromCart = cartFromSession.getCartItemDtos();
            for (CartItemDTO cartItem : itemFromCart) {
                if (cartItem.getProductDTO().getId().equals(cartItemToDelete)) {
                    itemFromCart.remove(cartItem);
                    calculateTotalPriceAfterDelete(cartFromSession, cartItem);
                    break;
                }
            }
        }
    }

    @Override
    public void addToCartWhenUserIsLogged(CartDTO cartDTO, Product newProductToAdd, User userLogged) {
        if (userLogged != null) {
            try {
                Cart cart = getByUserId(userLogged.getId());
                List<CartItem> cartItemDB = cart.getCartItems();
                boolean isUpdated = false;
                updateCartWhenUserIsLogged(newProductToAdd, cart, cartItemDB, isUpdated);
            } catch (CartException e) {
                cartDTO.setUserDTO(userConverter.entityToDto(userLogged));
                save(cartConverter.dtoToEntity(cartDTO));
            }
        }
    }

    @Override
    public Cart merge(UserDTO user, CartDTO cartFromSession) {
        Cart cart = new Cart();
        if (user != null) {
            Cart cartFromDB = null;
            try {
                cartFromDB = getByUserId(user.getId());
                cart = cartFromDB;
            } catch (CartException e) {
                e.printStackTrace();
            }
            if (cartFromSession != null && cartFromDB == null) {
                cartFromSession.setUserDTO(user);
                save(cartConverter.dtoToEntity(cartFromSession));
                cart = cartConverter.dtoToEntity(cartFromSession);
            } else if (cartFromSession != null) {
                CartDTO mergedCart = mergeCarts(cartFromSession, cartConverter.entityToDto(cartFromDB));
                mergedCart.setUserDTO(user);
                remove(cartFromDB);
                save(cartConverter.dtoToEntity(mergedCart));
                cart = cartConverter.dtoToEntity(mergedCart);
            }
        }
        return cart;
    }


    CartDTO mergeCarts(CartDTO cartFromSession, CartDTO cartFromDB) {
        List<CartItemDTO> cartItemFromCartSession = cartFromSession.getCartItemDtos();
        List<CartItemDTO> cartItemFromCartDB = cartFromDB.getCartItemDtos();
        List<CartItemDTO> newCartItem = new ArrayList<>();
        List<CartItemDTO> cartItemMerged;
        CartDTO cartMerged = new CartDTO();
        for (CartItemDTO cartItemDB : cartItemFromCartDB) {
            boolean isQuantityModified = false;
            ProductDTO productDTO = cartItemDB.getProductDTO();
            CartItemDTO itemInCart = new CartItemDTO();
            for (CartItemDTO cartItemSession : cartItemFromCartSession) {
                if (isProductInCartItem(productDTO, cartItemSession)) {
                    cartItemSession.setQuantity(cartItemSession.getQuantity() + cartItemDB.getQuantity());
                    isQuantityModified = true;
                }
            }
            if (!isQuantityModified) {
                itemInCart.setProductDTO(productDTO);
                itemInCart.setQuantity(cartItemDB.getQuantity());
                newCartItem.add(itemInCart);
            }
        }
        cartItemMerged = cartItemFromCartSession;
        cartItemMerged.addAll(newCartItem);
        cartMerged.setCartItemDtos(cartItemMerged);
        cartMerged.setTotalPrice(cartFromDB.getTotalPrice() + cartFromSession.getTotalPrice());
        return cartMerged;
    }


    public boolean isProductInCartItem(ProductDTO productDTO, CartItemDTO cartItemDTO) {

        return cartItemDTO.getProductDTO().getId().equals(productDTO.getId());
    }

    private void setQuantityAndTotalPrice(Integer newQuantity, Cart cartFromDB, CartItem cartItem) {
        if (newQuantity >= 1) {
            calculateTotalPriceWithNewQuantity(newQuantity, cartFromDB, cartItem);
            cartItem.setQuantity(newQuantity);
            update(cartFromDB);
            return;
        }
    }

    private void calculateTotalPriceWithNewQuantity(Integer newQuantity, Cart cartFromDB, CartItem cartItem) {
        cartFromDB.setTotalPrice(cartFromDB.getTotalPrice() - (cartItem.getQuantity() * cartItem.getProduct().getPrice()));
        cartFromDB.setTotalPrice(cartFromDB.getTotalPrice() + (newQuantity * cartItem.getProduct().getPrice()));
    }

    private void setQuantityAndTotalPrice(Integer newQuantity, CartDTO cartFromSession, CartItemDTO cartItem) {
        if (newQuantity >= 1) {
            calculateTotalPriceWithNewQuantity(newQuantity, cartFromSession, cartItem);
            cartItem.setQuantity(newQuantity);
            return;
        }
    }

    private void calculateTotalPriceWithNewQuantity(Integer newQuantity, CartDTO cartFromSession, CartItemDTO cartItem) {
        cartFromSession.setTotalPrice(cartFromSession.getTotalPrice() - (cartItem.getQuantity() * cartItem.getProductDTO().getPrice()));
        cartFromSession.setTotalPrice(cartFromSession.getTotalPrice() + (newQuantity * cartItem.getProductDTO().getPrice()));
    }

    private void calculateTotalPriceAfterDelete(CartDTO cartFromSession, CartItemDTO cartItem) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        cartFromSession.setTotalPrice(Double.valueOf(decimalFormat.format(cartFromSession.getTotalPrice() - (cartItem.getQuantity() * cartItem.getProductDTO().getPrice()))));
    }

    private void calculateTotalPriceAfterDelete(Cart cartFromDB, CartItem cartItem) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        cartFromDB.setTotalPrice(Double.valueOf(decimalFormat.format(cartFromDB.getTotalPrice() - (cartItem.getQuantity() * cartItem.getProduct().getPrice()))));
    }

    private void updateCart(CartDTO cartDTO, boolean isItemInCart, ProductDTO productDTO, List<CartItemDTO> cartItemDTOS, CartItemDTO itemInCart) {
        double totalPriceOfCart;
        if (isItemInCart) {
            itemInCart.setQuantity(itemInCart.getQuantity() + 1);
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            totalPriceOfCart = Double.valueOf(decimalFormat.format(cartDTO.getTotalPrice() + itemInCart.getProductDTO().getPrice()));
            cartDTO.setTotalPrice(totalPriceOfCart);
        } else {
            itemInCart.setProductDTO(productDTO);
            itemInCart.setQuantity(1);
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            totalPriceOfCart = Double.valueOf(decimalFormat.format(cartDTO.getTotalPrice() + itemInCart.getProductDTO().getPrice()));
            cartDTO.setTotalPrice(totalPriceOfCart);
            cartItemDTOS.add(itemInCart);
        }
    }

    private CartDTO createCartAfterFirstItemIsAdded(ProductDTO productDTO) {
        List<CartItemDTO> cartItemDTOS;
        CartDTO cartDTO;
        cartDTO = new CartDTO();
        cartItemDTOS = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductDTO(productDTO);
        cartItemDTO.setQuantity(1);
        cartItemDTOS.add(cartItemDTO);
        cartDTO.setCartItemDtos(cartItemDTOS);
        cartDTO.setTotalPrice(productDTO.getPrice());
        return cartDTO;
    }

    private void updateCartWhenUserIsLogged(Product newProductToAdd, Cart cart, List<CartItem> cartItemDB, boolean isUpdated) {
        for (CartItem cartItem : cartItemDB) {
            if (cartItem.getProduct().getId().equals(newProductToAdd.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cart.setTotalPrice(cart.getTotalPrice() + cartItem.getProduct().getPrice());
                update(cart);
                isUpdated = true;

            }
        }
        if (!isUpdated) {
            CartItem newCartItemToAdd = new CartItem();
            newCartItemToAdd.setProduct(newProductToAdd);
            newCartItemToAdd.setQuantity(1);
            cartItemDB.add(newCartItemToAdd);
            cart.setTotalPrice(cart.getTotalPrice() + newProductToAdd.getPrice());
            update(cart);
        }
    }
}
