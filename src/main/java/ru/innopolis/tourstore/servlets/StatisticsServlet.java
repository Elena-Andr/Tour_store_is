package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.exception.OrderDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for displaying sales statistics
 */
public class StatisticsServlet extends HttpServlet {
    private DatabaseConnector dbConnector = new DatabaseConnector();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            OrderDao orderDao = new OrderDao(dbConnector);
            List<Order> orders = orderDao.getAll();
            req.setAttribute("orders", orderDao.getOrderInfos(orders));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/OrdersView.jsp");
            dispatcher.forward(req, resp);
        } catch (OrderDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
