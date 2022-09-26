package com.bercomic.youngculture.service;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.dto.ProductHistoryDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> getByUser(Long userId);
    void placeOrder(UserDTO userLogged, CartDTO cartOfUser, List<CartItemDTO> itemsFromCart, List<ProductHistoryDTO> productHistories);
}
