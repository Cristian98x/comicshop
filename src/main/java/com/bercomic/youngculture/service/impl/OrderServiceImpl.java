package com.bercomic.youngculture.service.impl;

import com.bercomic.youngculture.converter.CartConverter;
import com.bercomic.youngculture.converter.OrderConverter;
import com.bercomic.youngculture.dao.OrderDAO;
import com.bercomic.youngculture.dao.impl.OrderDaoImpl;
import com.bercomic.youngculture.dto.*;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;
import com.bercomic.youngculture.model.Order;
import com.bercomic.youngculture.service.CartService;
import com.bercomic.youngculture.service.OrderService;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private CartFacade cartFacade = new CartFacadeImpl();
    private OrderConverter orderConverter = new OrderConverter();

    public OrderServiceImpl(){
        orderDAO = new OrderDaoImpl();
    }

    @Override
    public void save(Order order) {
        orderDAO.openCurrentSession();
        orderDAO.save(order);
        orderDAO.closeCurrentSession();
    }

    @Override
    public List<Order> getByUser(Long userId) {
        orderDAO.openCurrentSessionWithTransaction();
        List<Order> order = orderDAO.getByUser(userId);
        orderDAO.openCurrentSessionWithTransaction();
        return order;
    }

    @Override
    public void placeOrder(UserDTO userLogged, CartDTO cartOfUser, List<CartItemDTO> itemsFromCart, List<ProductHistoryDTO> productHistories) {
        for(CartItemDTO cartItemDTO: itemsFromCart){
            productHistoryMapper(productHistories, cartItemDTO);
        }
        OrderDTO orderOfUserDTO = new OrderDTO();
        orderMapper(userLogged, cartOfUser, productHistories, orderOfUserDTO);
        save(orderConverter.dtoToEntity(orderOfUserDTO));
        cartFacade.remove(cartOfUser);
    }

    private void productHistoryMapper(List<ProductHistoryDTO> productHistories, CartItemDTO cartItemDTO) {
        ProductHistoryDTO productHistory = new ProductHistoryDTO();
        productHistory.setQuantity(cartItemDTO.getQuantity());
        productHistory.setPrice(cartItemDTO.getProductDTO().getPrice());
        productHistory.setName(cartItemDTO.getProductDTO().getName());
        productHistories.add(productHistory);
    }

    private void orderMapper(UserDTO userLogged, CartDTO cartOfUser, List<ProductHistoryDTO> productHistories, OrderDTO orderOfUserDTO) {
        orderOfUserDTO.setUserDTO(userLogged);
        orderOfUserDTO.setStatus("PENDING");
        orderOfUserDTO.setProductHistoryDTO(productHistories);
        orderOfUserDTO.setPrice(cartOfUser.getTotalPrice());
        UUID number = UUID.randomUUID();
        orderOfUserDTO.setNumber(String.valueOf(number));
    }
}
