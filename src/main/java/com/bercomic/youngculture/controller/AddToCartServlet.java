package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.facade.ProductFacade;
import com.bercomic.youngculture.facade.impl.ProductFacadeImpl;
import com.bercomic.youngculture.service.CartService;
import com.bercomic.youngculture.service.impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class AddToCartServlet extends HttpServlet {
    private ProductFacade productFacade;
    private CartService cartService;

    public AddToCartServlet() {
        productFacade = new ProductFacadeImpl();
        cartService = new CartServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
        boolean isItemInCart = false;
        String productId = request.getParameter("productId");
        ProductDTO productDTO = productFacade.getById(productId);
        session.setAttribute("newProduct",productDTO);
        cartDTO = cartService.addToCart(cartDTO, isItemInCart, productDTO);
        session.setAttribute("cart", cartDTO);
    }
}
