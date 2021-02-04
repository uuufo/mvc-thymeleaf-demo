package dev.jlarsen.mvcthymeleafdemo.services;

import dev.jlarsen.mvcthymeleafdemo.models.User;
import dev.jlarsen.mvcthymeleafdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getUser(email);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(email)
                .password(user.getPassword())
                .roles("USER")
                .build();
//        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
//        auth.add(new SimpleGrantedAuthority("USER"));
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, user.getPassword(),
//                true, true, true, true, auth);
        return userDetails;
    }
}
