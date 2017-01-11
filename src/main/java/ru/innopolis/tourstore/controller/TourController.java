package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.InvalidInputDataException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.service.TourService;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.Predicate;

/**
 * Controller which handles actions with tours
 */
@Controller
public class TourController extends AbstractController {

    private TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @ModelAttribute("tour")
    public Tour createTour() {
        return new Tour();
    }

    @RequestMapping("/store")
    public String getToursList(Model model, HttpSession session) throws TourDaoException{
        //Show only not deleted tours
        List<Tour> availableTours = tourService.getAll();
        Predicate<Tour> tourPredicate = Tour::isDeleted;
        availableTours.removeIf(tourPredicate);

        model.addAttribute("tours", availableTours);

        User user = (User)session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }

        return "ToursView";
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.GET)
    public String getTourCreationPage(){
        return "CreateTour";
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.POST)
    public String createNewTour(@ModelAttribute("tour") Tour tour)
            throws TourDaoException, InvalidInputDataException {

        checkInputData(tour);
        tour.setDeleted(false);
        tourService.create(tour);

        return "redirect:/store";
    }

    @RequestMapping("/store/delete")
    public String deleteTour(@RequestParam("id") int tourIdToDelete) throws TourDaoException {

        //Mark tour entity as deleted
        Tour tourToDelete = tourService.getEntityById(tourIdToDelete);
        tourToDelete.setDeleted(true);
        tourService.update(tourToDelete);

        return "redirect:/store";
    }

    @RequestMapping(path = "/store/edit", method = RequestMethod.GET)
    public String getEditTourPage(Model model, @RequestParam("id") int tourIdToEdit) throws TourDaoException {

        Tour tour = tourService.getEntityById(tourIdToEdit);
        model.addAttribute("tour", tour);
        return "EditTour";
    }

    @RequestMapping(path = "/store/edit", method = RequestMethod.POST)
    public String editTour(@ModelAttribute("tour") Tour tour)
            throws TourDaoException, InvalidInputDataException {

        checkInputData(tour);
        tourService.update(tour);
        return "redirect:/store";
    }

    private void checkInputData(Tour tour) throws InvalidInputDataException {
        if(tour.getName().trim().length() == 0 || tour.getDescription().trim().length() == 0){
            throw new InvalidInputDataException("Please specify correct tour information");
        }
    }
}
