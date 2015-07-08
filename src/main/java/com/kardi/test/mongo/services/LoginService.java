package com.kardi.test.mongo.services;

import com.kardi.test.mongo.entities.User;

public interface LoginService {

    User getUser(String login);

}
