package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.InvalidInputDataException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.UserService;
import javax.servlet.http.HttpSession;


@Controller
public class UserController extends AbstractController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User createUser() {
        return new User();
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user") User user)
            throws InvalidInputDataException, UserDaoException {

            //Check if input is not empty
            if (user.getName().trim().length() == 0 || user.getPassword().trim().length() == 0) {
                throw new InvalidInputDataException("Name or password invalid");
            }

            //Check if the user name is unique
            if (userService.isUserAlreadyRegistered(user.getName())) {
                throw new InvalidInputDataException("User is already registered");
            }

            user.setRole("user");

            userService.create(user);
            return "registerSuccess";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute("user") User user) throws UserDaoException {
        User validUser = userService.validateLogin(user);
        if (validUser == null) {
            return "loginFailed";
        }

        session.setAttribute("user", validUser);
        return "redirect:/store";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/store";
    }
}
