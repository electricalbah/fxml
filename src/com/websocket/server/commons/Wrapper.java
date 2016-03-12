/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 *
 * @author mamadu
 * @param <T>
 */
public class Wrapper<T> {

    private final T result;

      @JsonCreator
    public Wrapper(@JsonProperty("result") T result) {
        this.result = result;
    }
    public T getResult() {
        return result;
    }
}
