package com.kardi.test.mongo.services;

import java.util.ArrayList;
import java.util.List;

import com.kardi.test.mongo.beans.MongoConnectionFactory;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.*;

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

    public void update(final Document document) throws Exception {
        MongoCollection<Document> collection = getCollection();
        collection.updateOne(eq("name", document.getString("name")),
                new Document("$set", document)
                //, new UpdateOptions().upsert(true) // creates new fields if it doesn't exist
        );
    }

    @Override
    public Document getById() {

        // 1st way
//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 10).append("$lt", 90));

        // 2nd way
//        Bson filter = and( eq("x", 0), gt("y", 10), lt("y", 90));

        return null;
    }

    @Override
    public List<Document> getAll() throws Exception {
        MongoCollection<Document> collection = getCollection();
        return collection.find().into(new ArrayList<Document>());
    }

    @Override
    public List<String> getAllNames() throws Exception {
        MongoCollection<Document> collection = getCollection();

        // ---------- PROJECTION ----------
        // Bson projection = new Document("age", 0).append("profession", 0).append("_id", 0);
        // or
        // Bson projection = Projections.exclude("age", "profession", "_id");
        // or
        Bson projection = Projections.fields(
                Projections.include("name"),
                Projections.exclude("age", "profession", "_id")
        );

        // ---------- SORTING ----------
        Bson sort = new Document("name", -1);
        // or
        // sort = Sorts.descending("name");
        // or
        // sort = Sorts.orderBy(Sorts.descending("name"), Sorts.ascending("age"));


        List<Document> documents = collection.find()
                .projection(projection)
                .sort(sort)
                //.skip(1)
                //.limit(10)
                .into(new ArrayList<Document>());
        List<String> names = new ArrayList<String>();
        for (Document document : documents) {
            names.add(document.getString("name"));
        }
        return names;
    }

    private MongoCollection<Document> getCollection() throws Exception {
        MongoClient client = mongoConnectionFactory.getMongoClient();
        MongoDatabase database = client.getDatabase(DATABASE);
        return database.getCollection(COLLECTION);
    }
}
