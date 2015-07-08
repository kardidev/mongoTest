package com.kardi.test.mongo.services;

import java.util.HashSet;
import java.util.Set;

import com.kardi.test.mongo.entities.User;
import com.kardi.test.mongo.security.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by m.lysenchuk on 7/8/15.
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = loginService.getUser(login);
        // set user roles
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }

}
