package ru.innopolis.tourstore.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController {

//    @ExceptionHandler({OrderDaoException.class, UserDaoException.class, TourDaoException.class})
//    public ModelAndView handleDaoErrors(Exception ex){
//        ModelAndView modelAndView = new ModelAndView("exceptionPage");
//        modelAndView.addObject("exception", ex.getMessage());
//        return modelAndView;
//    }
//
//    @ExceptionHandler(InvalidInputDataException.class)
//    public ModelAndView handleInputError(InvalidInputDataException ex){
//        ModelAndView modelAndView = new ModelAndView("exceptionPage");
//        modelAndView.addObject("exception", ex.getMessage());
//        return modelAndView;
//    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleMainError(Exception ex){
        ModelAndView modelAndView = new ModelAndView("exceptionPage");
        modelAndView.addObject("exception", ex.getMessage());
        return modelAndView;
    }
}
