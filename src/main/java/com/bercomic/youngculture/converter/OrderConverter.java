package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.OrderDTO;
import com.bercomic.youngculture.dto.ProductHistoryDTO;
import com.bercomic.youngculture.model.Order;
import com.bercomic.youngculture.model.ProductHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderConverter {
    private UserConverter userConverter = new UserConverter();
    private ProductHistoryConverter productHistoryConverter = new ProductHistoryConverter();

    public Order dtoToEntity(OrderDTO orderDTO){
        Order order = new Order();
        order.setNumber(UUID.fromString(orderDTO.getNumber()));
        order.setPrice(orderDTO.getPrice());
        order.setUser(userConverter.dtoToEntity(orderDTO.getUserDTO()));
        order.setStatus(orderDTO.getStatus());
        List<ProductHistoryDTO> productHistoryDTO = orderDTO.getProductHistoryDTO();
        List<ProductHistory> productHistories = new ArrayList<>();
        for(ProductHistoryDTO product:productHistoryDTO){
            ProductHistory productHistory = productHistoryConverter.dtoToEntity(product);
            productHistories.add(productHistory);
        }
        order.setProductHistories(productHistories);
        return order;
    }

    public OrderDTO entityToDto(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserDTO(userConverter.entityToDto(order.getUser()));
        orderDTO.setNumber(order.getNumber().toString());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setStatus(order.getStatus());
        List<ProductHistory> productHistories = order.getProductHistories();
        List<ProductHistoryDTO> productHistoryDTO = new ArrayList<>();
        for(ProductHistory productHistory:productHistories){
            ProductHistoryDTO productHistDTO = productHistoryConverter.entityToDto(productHistory);
            productHistoryDTO.add(productHistDTO);
        }
        orderDTO.setProductHistoryDTO(productHistoryDTO);
        return orderDTO;
    }
}
