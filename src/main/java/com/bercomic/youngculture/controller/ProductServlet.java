package com.bercomic.youngculture.controller;

import com.bercomic.youngculture.constants.ProductConstant;
import com.bercomic.youngculture.constants.UserConstant;
import com.bercomic.youngculture.dto.CategoryDTO;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.facade.CategoryFacade;
import com.bercomic.youngculture.facade.ProductFacade;
import com.bercomic.youngculture.facade.impl.CategoryFacadeImpl;
import com.bercomic.youngculture.facade.impl.ProductFacadeImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductFacade productFacade;

    public ProductServlet() {
        productFacade = new ProductFacadeImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        List<ProductDTO> productDtos = productFacade.getByCategory(category);
        request.setAttribute("product", productDtos);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
