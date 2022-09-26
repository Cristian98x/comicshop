package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.OrderConverter;
import com.bercomic.youngculture.dto.*;
import com.bercomic.youngculture.facade.OrderFacade;
import com.bercomic.youngculture.model.Order;
import com.bercomic.youngculture.service.OrderService;
import com.bercomic.youngculture.service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OrderFacadeImpl implements OrderFacade {
    private OrderService orderService = new OrderServiceImpl();
    private OrderConverter orderConverter = new OrderConverter();
    @Override
    public void save(OrderDTO orderDTO) {
        orderService.save(orderConverter.dtoToEntity(orderDTO));
    }

    @Override
    public List<OrderDTO> getByUser(Long userId) {
        List<Order> orders =  orderService.getByUser(userId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order order:orders){
            orderDTOS.add(orderConverter.entityToDto(order));
        }
        return orderDTOS;
    }

    @Override
    public void placeOrder(UserDTO userLogged, CartDTO cartOfUser, List<CartItemDTO> itemsFromCart, List<ProductHistoryDTO> productHistories) {
            orderService.placeOrder(userLogged,cartOfUser,itemsFromCart,productHistories);
    }
}
