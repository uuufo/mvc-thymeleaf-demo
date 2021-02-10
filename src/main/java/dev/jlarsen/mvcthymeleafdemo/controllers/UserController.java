package dev.jlarsen.mvcthymeleafdemo.controllers;

import dev.jlarsen.mvcthymeleafdemo.exceptions.UserExistsException;
import dev.jlarsen.mvcthymeleafdemo.facades.UserFacade;
import dev.jlarsen.mvcthymeleafdemo.models.UserDto;
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
    UserFacade userFacade;

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userFacade.getUserList());
        return "users";
    }

    @GetMapping("/denied")
    public String accessDenied() {
        return "denied";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        userFacade.populateModel(model);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model) throws UserExistsException {
        if (bindingResult.hasErrors()) {
            userFacade.populateModel(model);
            return "register_form";
        } else {
            userFacade.registerNewUserAccount(user);
            return "register_success";
        }
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        userFacade.populateProfile(model, principal);
        return "profile";
    }

    @PostMapping("/profile")
    public String submitProfileForm(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model, Principal principal) {
        System.out.println("test");
        if (bindingResult.hasErrors()) {
            userFacade.populateProfile(model, principal);
            return "profile";
        } else {
            userFacade.updateUserAccountProfile(user, principal);
            return "profile_success";
        }
    }
}