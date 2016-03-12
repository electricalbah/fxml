///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.websocket.server.dtos;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonSubTypes;
//
///**
// *
// * @author mamadu
// */
////@JsonSubTypes({    
////    @JsonSubTypes.Type(value = ReceiverInfo.class, name = "receiverInfo") })  
//public class ReceiverInfo extends UserInfo{
//
//    private int numberOfOpenTabs;
//
//    
//    //Since this Nested class in the Message object has a constructor other than the default one
//    //then we must annotate the constructor parameters
//    //http://stackoverflow.com/questions/7625783/jsonmappingexception-no-suitable-constructor-found-for-type-simple-type-class
//    public ReceiverInfo(@JsonProperty("name")String name, @JsonProperty("id")String id) {
//        super(name, id);
//    }
//
//    public int getNumberOfOpenTabs() {
//        return numberOfOpenTabs;
//    }
//
//    public void setNumberOfOpenTabs(int numberOfOpenTabs) {
//        this.numberOfOpenTabs = numberOfOpenTabs;
//    }
//}
