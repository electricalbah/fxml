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
public class SenderInfo extends UserInfo {

    //Since this Nested class in the Message object has a constructor other than the default one
    //then we must annotate the constructor parameters
    //http://stackoverflow.com/questions/7625783/jsonmappingexception-no-suitable-constructor-found-for-type-simple-type-class

    public SenderInfo(@JsonProperty("name") String name, @JsonProperty("sessionToken") String sessionToken, @JsonProperty("deviceType") String deviceType) {
        super(name, sessionToken);
        this.deviceType = deviceType;
    }
    private String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
