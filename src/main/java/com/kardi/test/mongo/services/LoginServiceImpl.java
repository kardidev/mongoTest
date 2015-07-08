package com.kardi.test.mongo.services;

import com.kardi.test.mongo.entities.User;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUser(String login) {
        return new User(login, "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
    }

}
