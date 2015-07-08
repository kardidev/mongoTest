package com.kardi.test.mongo.services;

import com.kardi.test.mongo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = loginService.getUser(login);
        // set user roles
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("USER"));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        roles);

        System.out.println("> loadUserByUsername : " + user.getLogin());

        return userDetails;
    }

}
