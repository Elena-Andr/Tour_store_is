package ru.innopolis.tourstore.filter;

import ru.innopolis.tourstore.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Object is used for filtering users on specific pages
 */
public class UserCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user.getRole().equals("admin")){
            chain.doFilter(request, response);
        }else {
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
