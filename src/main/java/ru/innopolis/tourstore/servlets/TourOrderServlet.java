package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.OrderDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Servlet displays the tours ordered by a user
 */
public class TourOrderServlet extends HttpServlet {
    private DatabaseConnector dbConnector = new DatabaseConnector();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Add the tour to the user's orders
            int tourId = Integer.parseInt(req.getParameter("id"));
            User user = (User) req.getSession().getAttribute("user");

            Order order = new Order();
            order.setTourId(tourId);
            order.setUserId(user.getId());

            OrderDao orderDao = new OrderDao(dbConnector);
            orderDao.create(order);

            //Show all orders of the user
            List<Order> orders = orderDao.getAll();
            Predicate<Order> orderPredicate = (Order o) -> o.getUserId() != user.getId();
            orders.removeIf(orderPredicate);
            req.setAttribute("myOrders", orderDao.getOrderInfos(orders));

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/ShoppingCartView.jsp");
            requestDispatcher.forward(req, resp);
        } catch (OrderDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
