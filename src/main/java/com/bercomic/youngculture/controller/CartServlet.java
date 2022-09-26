package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CartServlet extends HttpServlet {

    private CartFacade cartFacade;

    public CartServlet() {
        cartFacade = new CartFacadeImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDTO cartDTO;
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            Long idUser = userDTO.getId();
            cartDTO = cartFacade.getByUserId(idUser);
        } else {
            cartDTO = (CartDTO) session.getAttribute("cart");
        }
        session.setAttribute("cart", cartDTO);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
        ProductDTO newProductToAdd = (ProductDTO) session.getAttribute("newProduct");
        UserDTO userLoggedDTO = (UserDTO) session.getAttribute("user");
        cartFacade.addToCartWhenUserIsLogged(cartDTO, newProductToAdd, userLoggedDTO);
    }

}
