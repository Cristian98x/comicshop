package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.*;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.OrderFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;
import com.bercomic.youngculture.facade.impl.OrderFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServlet extends HttpServlet {

    private OrderFacade orderFacade;

    public OrderServlet() {
        orderFacade = new OrderFacadeImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO userLogged = (UserDTO) session.getAttribute("user");
        List<OrderDTO> orderOfUser = orderFacade.getByUser(userLogged.getId());
        session.setAttribute("orders", orderOfUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("order.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO userLogged = (UserDTO) session.getAttribute("user");
        if (userLogged != null) {
            CartDTO cartOfUser = (CartDTO) session.getAttribute("cart");
            List<CartItemDTO> itemsFromCart = cartOfUser.getCartItemDtos();
            List<ProductHistoryDTO> productHistories = new ArrayList<>();
            if(cartOfUser.getTotalPrice()!=0){
                orderFacade.placeOrder(userLogged, cartOfUser, itemsFromCart, productHistories);
                cartOfUser = null;
                session.setAttribute("cart", cartOfUser);
            }else{
                response.sendError(402);
            }
        }else{
            response.sendError(401);
        }
    }
}
