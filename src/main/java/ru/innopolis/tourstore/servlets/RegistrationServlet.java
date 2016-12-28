package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.exception.UserDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for registering new users
 */
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        String userName = (req.getParameter("name"));
        String password = (req.getParameter("password"));

        //Check if input is not empty
        if(userName.trim().length() == 0 || password.trim().length() == 0){
            req.setAttribute("ERROR", "Name or password invalid");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        //Check if the user name is unique
        if(isUserAlreadyRegistered(userName)){
            req.setAttribute("ERROR", "User is already registered");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        User user = new User();
        user.setName(userName);

        byte[] hashedPassword = PasswordAuthentication.hashPassword(password, "");
        user.setPassword(hashedPassword);

        user.setRole("user");

        UserDao userDao = new UserDao();
        userDao.create(user);

        } catch (UserDaoException e){
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("registerSuccess.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean isUserAlreadyRegistered(String name) throws UserDaoException {

        UserDao userDao = new UserDao();
        List<User> userList = userDao.getAll();

        for(User user : userList){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
