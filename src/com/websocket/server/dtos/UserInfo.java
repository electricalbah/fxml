/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author mamadu
 */
public class UserInfo {
    //If this Nested class in the Message object has a constructor other than the default one
    //then we must annotate the constructor parameters
    //http://stackoverflow.com/questions/7625783/jsonmappingexception-no-suitable-constructor-found-for-type-simple-type-class
    public UserInfo(@JsonProperty("name")String name, @JsonProperty("sessionToken")String sessionToken) {
        this.name = name;
        this.sessionToken = sessionToken;
    }

    private String name;
    private String sessionToken;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String id) {
        this.sessionToken = id;
    }
}
