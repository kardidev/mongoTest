package com.kardi.test.mongo.beans;

import javax.annotation.PostConstruct;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import org.springframework.stereotype.Component;

@Component
public class MongoConnectionFactory {

    private MongoClient mongoClient;

    @PostConstruct
    public void init() {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .connectTimeout(30)
                .build();

        mongoClient = new MongoClient(new ServerAddress("localhost", 27017), options);
    }

    public MongoClient getMongoClient() throws Exception {
        if (mongoClient == null) {
            throw new Exception("MongoClient isn't initialized");
        }
        return mongoClient;
    }

}
