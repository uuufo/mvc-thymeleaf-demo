package dev.jlarsen.mvcthymeleafdemo.services;

import dev.jlarsen.mvcthymeleafdemo.exceptions.UserExistsException;
import dev.jlarsen.mvcthymeleafdemo.models.User;
import dev.jlarsen.mvcthymeleafdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String email) {
        if (emailExists(email)) {
            return userRepository.findByEmail(email);
        } else {
            throw new UsernameNotFoundException("User Not Found");
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
