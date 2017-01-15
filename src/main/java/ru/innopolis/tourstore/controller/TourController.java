package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.exception.InvalidInputDataException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.service.TourService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @RequestMapping("/store")
    public String getToursList(Model model, HttpServletRequest request)
            throws TourDaoException{

        //Show only not deleted tours
        List<Tour> availableTours = tourService.getAll();
        Predicate<Tour> tourPredicate = Tour::isDeleted;
        availableTours.removeIf(tourPredicate);

        model.addAttribute("tours", availableTours);

        //Add current user name and role to model
        if(request.getUserPrincipal() != null){
            model.addAttribute("username", request.getUserPrincipal().getName());
            model.addAttribute("user_role", request.isUserInRole("ROLE_USER") ? "ROLE_USER" : "ROLE_ADMIN");
        }else {
            model.addAttribute("username", "guest");
            model.addAttribute("user_role", "guest");
        }

        return "ToursView";
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.GET)
    public String getTourCreationPage(Model model){
        model.addAttribute("tour", new Tour());
        return "CreateTour";
    }

    @RequestMapping(path = "/store/create", method = RequestMethod.POST)
    public String createNewTour(@Valid Tour tour, BindingResult bindingResult)
            throws TourDaoException, InvalidInputDataException {

        if(bindingResult.hasErrors()){
            return "CreateTour";
        }

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
    public String getEditTourPage(Model model,
                                  @RequestParam("id") int tourIdToEdit)
            throws TourDaoException {

        Tour tour = tourService.getEntityById(tourIdToEdit);
        model.addAttribute("tour", tour);
        return "EditTour";
    }

    @RequestMapping(path = "/store/edit", method = RequestMethod.POST)
    public String editTour(@Valid Tour tour, BindingResult bindingResult)
            throws TourDaoException, InvalidInputDataException {

        if(bindingResult.hasErrors()){
            return "EditTour";
        }

        tourService.update(tour);
        return "redirect:/store";
    }
}
