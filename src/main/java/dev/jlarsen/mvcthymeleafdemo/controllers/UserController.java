package dev.jlarsen.mvcthymeleafdemo.controllers;

import dev.jlarsen.mvcthymeleafdemo.exceptions.UserExistsException;
import dev.jlarsen.mvcthymeleafdemo.models.User;
import dev.jlarsen.mvcthymeleafdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/denied")
    public String accessDenied() {
        return "denied";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        userService.populateModel(model);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) throws UserExistsException {
        if (bindingResult.hasErrors()) {
            userService.populateModel(model);
            return "register_form";
        } else {
            userService.registerNewUserAccount(user);
            return "register_success";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        userService.populateProfile(model, principal);
        return "profile";
    }
}