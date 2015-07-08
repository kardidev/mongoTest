package com.kardi.test.mongo.dtos;

/**
 * Created by m.lysenchuk on 7/6/15.
 */
public class DefaultResponse {

    public boolean result = true;
    public String message = "OK";

    public static DefaultResponse getOK() {
        return new DefaultResponse();
    }

    public static DefaultResponse getError(String msg) {
        DefaultResponse resp = new DefaultResponse();
        resp.result = false;
        resp.message = msg;
        return resp;
    }
}
