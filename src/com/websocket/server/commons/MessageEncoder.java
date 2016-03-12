/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.commons;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.websocket.server.dtos.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;



/**
 *
 * @author mamadu
 */


public class MessageEncoder implements Encoder.Text<Message> {

  @Override
  public String encode(Message message) throws EncodeException {
        System.out.println("ENCODER FIRED ");
      String output ="";
      try {
          // JsonObject jsonObject = Json.createObjectBuilder()
//        .add("subject", message.getSubject())
//        .add("content", message.getContent()).build();
          //         return jsonObject.toString();
          output = JsonPojoMapper.toJson(message, true);
      } catch (JsonGenerationException ex) {
          Logger.getLogger(MessageEncoder.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(MessageEncoder.class.getName()).log(Level.SEVERE, null, ex);
      }

     return output;
        
        
        
//   USING JAVASE     
//        JsonObjectBuilder personObjectBuilder = Json.createObjectBuilder();
//        JsonObject personObject = personObjectBuilder
//                .add("message", message.getMessage())
//                .add("timestamp", System.currentTimeMillis() / 1000L)
//                .add("userCount", message.getUserCount())
//                .add("senderInfo", 
//                     Json.createObjectBuilder().add("name", message.getSenderInfo().getName())
//                                               .add("id", message.getSenderInfo().getId())
//                                               .add("sessioncount", message.getSenderInfo().getSessioncount())
//                                               .build()
//                      )
//                    .add("ReceiverInfo", 
//                     Json.createObjectBuilder().add("name", message.getReceiverInfo().getName())
//                                               .add("id", message.getReceiverInfo().getId())
//                                               .add("sessioncount", message.getReceiverInfo().getSessioncount())
//                                               .build()
//                    )
//                .build();
//        
//        return personObject.toString();
  }

  @Override
  public void init(EndpointConfig ec) {
    System.out.println("MessageEncoder - init method called");
  }

  @Override
  public void destroy() {
    System.out.println("MessageEncoder - destroy method called");
  }

}