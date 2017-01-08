package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.UserService;
import ru.innopolis.tourstore.filter.PasswordAuthentication;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String createNewUser(Model model,
                                @RequestParam("name") String userName,
                                @RequestParam("password") String password) {

        try {
            //Check if input is not empty
            if (userName.trim().length() == 0 || password.trim().length() == 0) {
                return handleError(model, "Name or password invalid");
            }

            //Check if the user name is unique
            if (userService.isUserAlreadyRegistered(userName)) {
                return handleError(model, "User is already registered");
            }

            User user = new User();
            user.setName(userName.trim());

            byte[] hashedPassword = PasswordAuthentication.hashPassword(password.trim(), new byte[]{});
            user.setPassword(hashedPassword);

            user.setRole("user");

            userService.create(user);
            return "registerSuccess";

        } catch (UserDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(Model model,
                        HttpSession session,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password){
        try{
            User user = userService.validateLogin(name.trim(), password.trim());
            if (user == null) {
                return "loginFailed";
            } else {
                session.setAttribute("user", user);
                session.setAttribute("userrole", user.getRole());
                session.setAttribute("username", user.getName());
                return "loginSuccess";
            }
    } catch (UserDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/store";
    }
}
