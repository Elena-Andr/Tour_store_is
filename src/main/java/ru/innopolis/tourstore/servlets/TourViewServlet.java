package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.dao.TourDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.RequestDispatcher;

/**
 * Main servlet which displays all available tours
 */
public class TourViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Show only not deleted tours
            DatabaseConnector dbConnector = new DatabaseConnector();
            TourDao tourDao = new TourDao(dbConnector);
            List<Tour> availableTours = tourDao.getAll();
            Predicate<Tour> tourPredicate = (Tour o) -> o.isDeleted();
            availableTours.removeIf(tourPredicate);

            req.setAttribute("tours", availableTours);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/ToursView.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
