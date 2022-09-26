package com.bercomic.youngculture.dao;

import com.bercomic.youngculture.model.Order;
import com.bercomic.youngculture.utils.SessionManager;

import java.util.List;

public interface OrderDAO extends SessionManager {
    void save(Order order);
    List<Order> getByUser(Long userId);
}
