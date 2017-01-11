package ru.innopolis.tourstore.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.tourstore.entity.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Object is used for filtering users on pages for admins only.
 */
public class AdminFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if(user == null){
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
                return;
            }

            if ( user.getRole().equals("admin")) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void destroy() {
    }
}
