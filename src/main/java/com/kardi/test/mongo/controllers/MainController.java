package com.kardi.test.mongo.controllers;

import com.kardi.test.mongo.beans.MongoConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private MongoConnectionFactory mongoConnectionFactory;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String hello() {
        return "index";
    }
}
