package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.dao.OrderDAO;
import com.bercomic.youngculture.model.Order;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends SessionManagerImpl implements OrderDAO {
    @Override
    public void save(Order order) {
        this.currentSession.save(order);
    }

    @Override
    public List<Order> getByUser(Long userId) {
        Query query = this.currentSession.createQuery("from Order o where o.user.id=:userid");
        query.setParameter("userid", userId);
        return(List<Order>)query.getResultList();
    }
}
