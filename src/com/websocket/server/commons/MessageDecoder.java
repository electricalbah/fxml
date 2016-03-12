/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.commons;

import com.fasterxml.jackson.core.JsonParseException;
import com.websocket.server.dtos.Message;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
//
//import javax.json.Json;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;


/**
 *
 * @author mamadu
 */
public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String jsonMessage) throws DecodeException {
        System.out.println("DENCODER FIRED ");
     

//              JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
//    Message message = new Message();
//    message.setSenderInfo(new SenderInfo());
//    message.setReceiverInfo(new ReceiverInfo());
//    
//    
//    message.setMessage(jsonObject.getString("message"));
//    message.setTimeStamp(jsonObject.getInt("timeStamp"));
//     message.setTimeStamp(jsonObject.getInt("userCount"));
//        JsonObject jsonSender = jsonObject.getJsonObject("SenderInfo");
//                           message.getSenderInfo().setName(jsonSender.getString("name"));
//                           message.getSenderInfo().setId(jsonSender.getString("id"));
//                           message.getSenderInfo().setSessioncount(jsonSender.getInt("sessioncount"));
//        JsonObject jsonReceiver = jsonObject.getJsonObject("ReceiverInfo");
//                           message.getReceiverInfo().setName(jsonReceiver.getString("name"));
//                           message.getReceiverInfo().setId(jsonReceiver.getString("id"));
//                           message.getReceiverInfo().setSessioncount(jsonReceiver.getInt("sessioncount"));
//        
           Message message = null;
          try {
            message = (Message) JsonPojoMapper.fromJson(jsonMessage, Message.class);
        } catch (JsonParseException ex) {
            Logger.getLogger(MessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message ;
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
           // Json.createReader(new StringReader(jsonMessage)).readObject(); //todo
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("MessageDecoder -init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called");
    }

}
