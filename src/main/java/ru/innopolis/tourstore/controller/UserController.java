package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.InvalidInputDataException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Controller which handles authorization actions
 */
@Controller
public class UserController{

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String createNewUser(@Valid UserEntity userForm, BindingResult bindingResult)
            throws InvalidInputDataException, UserDaoException {

        //Check if user already exists
        if(userService.isUserAlreadyRegistered(userForm.getName())){
            FieldError fieldError = new FieldError("user", "name", "User was already registered");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            return "register";
        }

        String hashedPassword = passwordEncoder.encode(userForm.getPassword());
        userForm.setPassword(hashedPassword);

        userForm.setRole("ROLE_USER");
        userForm.setEnabled(true);
        userService.create(userForm);
        return "registerSuccess";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam("error") boolean errorOccurred) throws Exception {
        if(errorOccurred){
            throw new Exception("Incorrect credentials");
        }
        return "login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/store";
    }
}
