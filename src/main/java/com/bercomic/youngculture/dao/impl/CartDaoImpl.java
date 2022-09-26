package com.bercomic.youngculture.dao.impl;

import com.bercomic.youngculture.constants.QueryConstant;
import com.bercomic.youngculture.dao.CartDAO;
import com.bercomic.youngculture.model.Cart;
import com.bercomic.youngculture.utils.impl.SessionManagerImpl;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

public class CartDaoImpl extends SessionManagerImpl implements CartDAO {

    @Override
    public void save(Cart cart) {

        this.currentSession.save(cart);
    }

    @Override
    public Cart findByUserId(Long idUser) {
        Query query =  this.currentSession.createQuery(QueryConstant.CART_BY_USER_ID);
        query.setParameter("userid",idUser);
        return (Cart)query.getSingleResult();
    }

    @Override
    public void update(Cart cart) {
        this.currentSession.update(cart);
    }

    @Transactional
    @Override
    public void remove(Cart cart) {
        Query query = this.currentSession.createQuery(QueryConstant.DELETE_CART_BY_ID);
        query.setParameter("id",cart.getId());
        query.executeUpdate();
    }
}
