package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.exception.UserDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet manages the user authentication
 */
public class UserValidatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher;

            String name = req.getParameter("name");
            String password = req.getParameter("password");

            User user = validateLogin(name, password);

            if (user == null) {
                requestDispatcher = req.getRequestDispatcher("loginFailed.jsp");

            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userrole", user.getRole());
                session.setAttribute("username", user.getName());
                requestDispatcher = req.getRequestDispatcher("loginSuccess.jsp");
            }
            requestDispatcher.forward(req, resp);
        } catch (UserDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }


    private User validateLogin(String name, String password) throws UserDaoException {

        if (name == null || password == null) {
            return null;
        }

        byte[] hashedPassword =PasswordAuthentication.hashPassword(password, "");

        UserDao userDao = new UserDao();
        List<User> users = userDao.getAll();

        for (User user : users) {
            if (user.getName().equals(name) && Arrays.equals(hashedPassword, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
