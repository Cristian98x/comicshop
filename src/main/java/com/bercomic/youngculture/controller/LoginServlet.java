package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.constants.UserConstant;
import com.bercomic.youngculture.dto.CartDTO;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.exception.CartException;
import com.bercomic.youngculture.facade.CartFacade;
import com.bercomic.youngculture.facade.UserFacade;
import com.bercomic.youngculture.facade.impl.CartFacadeImpl;
import com.bercomic.youngculture.facade.impl.UserFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserFacade userFacade;

    public LoginServlet() {
        userFacade = new UserFacadeImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(UserConstant.EMAIL);
        String password = request.getParameter(UserConstant.PASSWORD);
        UserDTO userDTO = userFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", userDTO);
    }
}
