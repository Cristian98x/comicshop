package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.dto.CategoryDTO;
import com.bercomic.youngculture.facade.CategoryFacade;
import com.bercomic.youngculture.facade.impl.CategoryFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopServlet extends HttpServlet {
    private CategoryFacade categoryFacade;

    public ShopServlet() {
        categoryFacade = new CategoryFacadeImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDTO> categoryDTOS = categoryFacade.findAll();
        request.setAttribute("category",categoryDTOS);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
