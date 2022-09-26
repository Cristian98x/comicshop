package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.constants.UserConstant;
import com.bercomic.youngculture.dto.UserDTO;
import com.bercomic.youngculture.facade.UserFacade;
import com.bercomic.youngculture.facade.impl.UserFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserFacade userFacade;

    public RegisterServlet() {
        userFacade = new UserFacadeImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(request.getParameter(UserConstant.FIRST_NAME));
        userDto.setLastName(request.getParameter(UserConstant.LAST_NAME));
        userDto.setEmail(request.getParameter(UserConstant.EMAIL));
        userDto.setPassword(request.getParameter(UserConstant.PASSWORD));
        boolean isUser = userFacade.isEmail(request.getParameter(UserConstant.EMAIL));
        if (isUser){
            response.sendError(402);
        }else{
            userFacade.save(userDto);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
