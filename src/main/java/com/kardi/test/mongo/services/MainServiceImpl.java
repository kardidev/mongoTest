package com.kardi.test.mongo.services;

import java.util.ArrayList;
import java.util.List;

import com.kardi.test.mongo.beans.MongoConnectionFactory;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    private static final String DATABASE = "javaTest";
    private static final String COLLECTION = "person";

    @Autowired
    private MongoConnectionFactory mongoConnectionFactory;


    @Override
    public void create(final Document document) throws Exception {
        MongoCollection<Document> collection = getCollection();
        collection.insertOne(document);
    }

    @Override
    public Document getById() {
        return null;
    }

    @Override
    public List<Document> getAll() throws Exception {
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterable = collection.find();
        iterable.limit(10);
        MongoCursor<Document> cursor = iterable.iterator();
        List<Document> documents = new ArrayList<Document>();
        while (cursor.hasNext()) {
            documents.add(cursor.next());
        }
        cursor.close();
        return documents;
    }

    private MongoCollection<Document> getCollection() throws Exception {
        MongoClient client = mongoConnectionFactory.getMongoClient();
        MongoDatabase database = client.getDatabase(DATABASE);
        return database.getCollection(COLLECTION);
    }
}
