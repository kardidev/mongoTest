package com.kardi.test.mongo.services;

import java.util.List;

import org.bson.Document;

public interface MainService {

    void create(Document document) throws Exception;

    Document getById();

    List<Document> getAll() throws Exception;

    List<String> getAllNames() throws Exception;

}
