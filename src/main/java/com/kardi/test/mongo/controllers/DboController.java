package com.kardi.test.mongo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.kardi.test.mongo.dtos.CollectionResponse;
import com.kardi.test.mongo.dtos.DefaultResponse;
import com.kardi.test.mongo.dtos.NamesResponse;
import com.kardi.test.mongo.dtos.Person;
import com.kardi.test.mongo.services.MainService;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by m.lysenchuk on 7/8/15.
 */
@RequestMapping("/mongo")
public class DboController {

    @Autowired
    private MainService mainService;

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

    @RequestMapping(value = "/getallnames", method = RequestMethod.GET, produces={"application/json"})
    public @ResponseBody DefaultResponse getAllNames() {
        try {
            NamesResponse response = new NamesResponse();
            response.names = mainService.getAllNames();
            return response;

        } catch (Exception ex) {
            return DefaultResponse.getError(ex.getMessage());
        }
    }
}
