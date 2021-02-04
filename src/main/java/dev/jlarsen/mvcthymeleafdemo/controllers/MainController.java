package dev.jlarsen.mvcthymeleafdemo.controllers;

import dev.jlarsen.mvcthymeleafdemo.exceptions.UserExistsException;
import dev.jlarsen.mvcthymeleafdemo.models.User;
import dev.jlarsen.mvcthymeleafdemo.repositories.UserRepository;
import dev.jlarsen.mvcthymeleafdemo.services.MainService;
import dev.jlarsen.mvcthymeleafdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    @GetMapping("/login")
//    public String loginUser() {
//        return "login";
//    }

    @GetMapping("/register")
    public String showForm(Model model) {
        mainService.populateModel(model);
        return "register_form";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "test/users";
    }

    @GetMapping("/test")
    public ModelAndView test(ModelAndView modelAndView) {
        mainService.populateModel(modelAndView);
        modelAndView.setViewName("register_form");
        return modelAndView;
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) throws UserExistsException {
        if (bindingResult.hasErrors()) {
            mainService.populateModel(model);
            return "register_form";
        } else {
            userService.registerNewUserAccount(user);
            return "register_success";
        }
    }
}