package dev.jlarsen.mvcthymeleafdemo.services;

import dev.jlarsen.mvcthymeleafdemo.models.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Service
public class MainService {
    public Model populateModel(Model model) {
        if (!model.containsAttribute("user")) {
            User user = new User();
            model.addAttribute("user", user);
        }
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return model;
    }

    public ModelAndView populateModel(ModelAndView modelAndView) {
        if (!modelAndView.getModelMap().containsAttribute("user")) {
            User user = new User();
            modelAndView.addObject("user", user);
        }
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        modelAndView.addObject("listProfession", listProfession);
        return modelAndView;
    }
}
