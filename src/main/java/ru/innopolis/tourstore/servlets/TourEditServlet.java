package ru.innopolis.tourstore.servlets;

import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.exception.TourDaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(req.getParameter("id"));
            TourDao tourDao = new TourDao();
            Tour tour = tourDao.getEntityById(id);

            req.setAttribute("id", id);
            req.setAttribute("name", tour.getName());
            req.setAttribute("description", tour.getDescription());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/EditTour.jsp");
            dispatcher.forward(req, resp);
        } catch (TourDaoException e) {
            req.setAttribute("ERROR", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           Tour tour = new Tour();
           tour.setId(Integer.valueOf(req.getParameter("id")));
           tour.setName(req.getParameter("name"));
           tour.setDescription(req.getParameter("description"));

           TourDao tourDao = new TourDao();
           tourDao.update(tour);

           resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/store"));
       } catch (TourDaoException e) {
           req.setAttribute("ERROR", e.getMessage());
           RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionPage.jsp");
           dispatcher.forward(req, resp);
       }
    }
}
