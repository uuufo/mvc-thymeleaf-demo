package dev.jlarsen.mvcthymeleafdemo.services;

import dev.jlarsen.mvcthymeleafdemo.exceptions.UserExistsException;
import dev.jlarsen.mvcthymeleafdemo.models.Role;
import dev.jlarsen.mvcthymeleafdemo.models.User;
import dev.jlarsen.mvcthymeleafdemo.repositories.RoleRepository;
import dev.jlarsen.mvcthymeleafdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String email) {
        if (emailExists(email)) {
            return userRepository.findByEmail(email);
        } else {
            throw new UsernameNotFoundException("User Not Found.");
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    public User registerNewUserAccount(User user) throws UserExistsException {
        if (emailExists(user.getEmail())) {
            throw new UserExistsException("There is an account with that email address:" + user.getEmail());
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole;
        if (user.isHuman()) {
            userRole = roleRepository.findByRole("ADMIN");
        } else {
            userRole = roleRepository.findByRole("USER");
        }
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        if (userRepository.findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Model populateModel(Model model) {
        if (!model.containsAttribute("user")) {
            User user = new User();
            model.addAttribute("user", user);
        }
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return model;
    }

    public Model populateProfile(Model model, Principal principal) {
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        User user = getUser(principal.getName());
        model.addAttribute(user);
        return model;
    }

}
