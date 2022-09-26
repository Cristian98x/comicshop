package com.bercomic.youngculture.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PasswordValidationFilter",urlPatterns = {"/register"})
public class PasswordValidationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(req.getMethod().equals("POST")) {
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordconfirm");
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("matchPassword", "false");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                res.sendError(403);
                requestDispatcher.include(request, response);
            }
            if (password.equals(passwordConfirm)) {
                chain.doFilter(request, response);
            }
        }else {
            chain.doFilter(request, response);
        }
    }
}
