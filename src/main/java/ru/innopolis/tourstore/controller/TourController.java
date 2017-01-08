package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.service.TourService;
import java.util.List;
import java.util.function.Predicate;

@Controller
public class TourController extends AbstractController {

    @Autowired
    private TourService tourService;

    @RequestMapping("/store")
    public String getToursList(Model model){

        try {
            //Show only not deleted tours
            List<Tour> availableTours = tourService.getAll();
            Predicate<Tour> tourPredicate = Tour::isDeleted;
            availableTours.removeIf(tourPredicate);

            model.addAttribute("tours", availableTours);

            return "ToursView";

        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.GET)
    public String getTourCreationPage(){
        return "CreateTour";
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.POST)
    public String createNewTour(Model model,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description ){
        try {
            Tour tour = new Tour();
            tour.setName(name);
            tour.setDescription(description);
            tour.setDeleted(false);
            tourService.create(tour);

            return getToursList(model);
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping("/store/delete")
    public String deleteTour(Model model, @RequestParam("id") int tourIdToDelete){
        try {
            //Mark tour object as deleted
            Tour tourToDelete = tourService.getEntityById(tourIdToDelete);
            tourToDelete.setDeleted(true);
            tourService.update(tourToDelete);

            return getToursList(model);
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping(path = "/store/edit", method = RequestMethod.GET)
    public String getEditTourPage(Model model, @RequestParam("id") int tourIdToEdit){
        try {
            Tour tour = tourService.getEntityById(tourIdToEdit);

            model.addAttribute("id", tour.getId());
            model.addAttribute("name", tour.getName());
            model.addAttribute("description", tour.getDescription());
           return "EditTour";
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping(path = "/store/edit", method = RequestMethod.POST)
    public String editTour(Model model,
                           @RequestParam("id") int tourId,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description){

        try {
            Tour tour = new Tour();
            tour.setId(tourId);
            tour.setName(name);
            tour.setDescription(description);

            tourService.update(tour);

            return getToursList(model);
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }
}
