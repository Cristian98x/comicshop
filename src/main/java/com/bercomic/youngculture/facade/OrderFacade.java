package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.*;

import java.util.List;

public interface OrderFacade {
    void save(OrderDTO orderDTO);
    List<OrderDTO> getByUser(Long userId);
    void placeOrder(UserDTO userLogged, CartDTO cartOfUser, List<CartItemDTO> itemsFromCart, List<ProductHistoryDTO> productHistories);
}
