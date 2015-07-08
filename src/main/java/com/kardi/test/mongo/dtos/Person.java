package com.kardi.test.mongo.dtos;

import org.bson.Document;

/**
 * Created by m.lysenchuk on 7/6/15.
 */
public class Person {

    public String id;
    public String name;
    public String age;
    public String profession;

    public Document createBson() {
        return new Document()
                .append("name", name)
                .append("age", age)
                .append("profession", profession);
    }

    public static Person fromBson(Document document) {
        Person person = new Person();
        person.id = document.get("_id").toString();
        person.name = document.getString("name");
        person.age = document.getString("age");
        person.profession = document.getString("profession");
        return person;
    }
}
