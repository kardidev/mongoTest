package com.kardi.test.mongo.entities;

public class User {

    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

//    public void setLogin(final String login) {
//        this.login = login;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(final String password) {
//        this.password = password;
//    }
}
