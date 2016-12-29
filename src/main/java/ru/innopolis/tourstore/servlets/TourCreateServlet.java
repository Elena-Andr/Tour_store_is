package ru.innopolis.tourstore.servlets;


import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.exception.TourDaoException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet handles new tour's creation
 */
public class TourCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/CreateTour.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Tour tour = new Tour();
            tour.setName(req.getParameter("name"));
            tour.setDescription(req.getParameter("description"));
            tour.setDeleted(false);

            DatabaseConnector dbConnector = new DatabaseConnector();
            TourDao tourDao = new TourDao(dbConnector);
            tourDao.create(tour);

            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/store"));
        } catch (TourDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
