package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.CartItemDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeCartServlet extends HttpServlet {

    private CartFacade cartFacade;

    public MergeCartServlet() {
        cartFacade = new CartFacadeImpl();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        CartDTO cartFromSession = (CartDTO) session.getAttribute("cart");
        CartDTO mergedCart = cartFacade.merge(userDTO,cartFromSession);
        session.setAttribute("cart",mergedCart);
    }
}
