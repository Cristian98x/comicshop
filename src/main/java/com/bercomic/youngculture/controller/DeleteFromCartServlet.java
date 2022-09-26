package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;



public class DeleteFromCartServlet extends HttpServlet {

    private CartFacade cartFacade;

    public DeleteFromCartServlet() {
        cartFacade = new CartFacadeImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loggedUser = (UserDTO) session.getAttribute("user");
        Long cartItemToDelete = Long.parseLong(request.getParameter("idOfProduct"));
        CartDTO cartFromSession = (CartDTO) session.getAttribute("cart");
        cartFacade.deleteFromCart(loggedUser, cartItemToDelete, cartFromSession);
    }
}
