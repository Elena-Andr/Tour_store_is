package ru.innopolis.tourstore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Abstract base class for Controllers
 */
@ControllerAdvice
public class ControllerExceptionsHandler {

    /**
     * Method handles all possible arised exceptions in controllers
     * @param ex - thrown exception
     * @return ModelAndView which represents exception page
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleMainError(Exception ex){
        ModelAndView modelAndView = new ModelAndView("exceptionPage");
        modelAndView.addObject("exception", ex.getMessage());
        return modelAndView;
    }
}
