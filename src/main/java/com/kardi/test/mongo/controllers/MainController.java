package com.kardi.test.mongo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.kardi.test.mongo.beans.MongoConnectionFactory;
import com.kardi.test.mongo.dtos.CollectionResponse;
import com.kardi.test.mongo.dtos.DefaultResponse;
import com.kardi.test.mongo.dtos.Person;
import com.kardi.test.mongo.services.MainService;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST,
            produces={"application/json"},
            consumes={"application/json"})
    @ResponseBody
    public DefaultResponse insert(@RequestBody Person person) {
        try {
            mainService.create(person.createBson());
        } catch (Exception ex) {
            return DefaultResponse.getError(ex.getMessage());
        }

        return DefaultResponse.getOK();
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces={"application/json"})
    public @ResponseBody DefaultResponse getAll() {

        try {
            List<Document> documents = mainService.getAll();
            CollectionResponse response = new CollectionResponse();
            response.persons = new ArrayList<Person>(documents.size());
            for (Document document : documents) {
                response.persons.add(Person.fromBson(document));
            }
            return response;

        } catch (Exception ex) {
            return DefaultResponse.getError(ex.getMessage());
        }

    }
}
