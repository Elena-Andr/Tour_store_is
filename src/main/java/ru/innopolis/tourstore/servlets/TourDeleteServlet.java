package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.exception.TourDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for deleting tours
 */
public class TourDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Mark tour object as deleted
            DatabaseConnector dbConnector = new DatabaseConnector();
            TourDao tourDao = new TourDao(dbConnector);
            Tour tourToDelete = tourDao.getEntityById(Integer.valueOf(req.getParameter("id")));
            tourToDelete.setDeleted(true);
            tourDao.update(tourToDelete);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/store"));

        } catch (TourDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
