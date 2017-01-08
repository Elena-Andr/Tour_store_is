package ru.innopolis.tourstore.controller;

import org.springframework.ui.Model;

public abstract class AbstractController {

    public String handleError(Model model, String errorMessage){
        model.addAttribute("ERROR", errorMessage);
        return "exceptionPage";
    }
}
