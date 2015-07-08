package com.kardi.test.mongo.services;

import java.util.List;

import org.bson.Document;

/**
 * Created by m.lysenchuk on 7/6/15.
 */
public interface MainService {

    void create(Document document) throws Exception;

    Document getById();

    List<Document> getAll() throws Exception;

}
