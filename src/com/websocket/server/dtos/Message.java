/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

/**
 *
 * @author mamadu
 */
public class Message {

    private String message;
    private String receiverToken;
    private boolean isSameUser;
    private int userCount;
    @JsonProperty("senderInfo")
    private SenderInfo senderInfo;
    @JsonProperty("messageType")
    private MessageType messageType;

    private List<UserInfo> allUserInfo;

    public List<UserInfo> getAllUserInfo() {
        return allUserInfo;
    }

    public void setAllUserInfo(List<UserInfo> allUserInfo) {
        this.allUserInfo = allUserInfo;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public boolean isIsSameUser() {
        return isSameUser;
    }

    public void setIsSameUser(boolean isSameUser) {
        this.isSameUser = isSameUser;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }
        public String getReceiverToken() {
        return receiverToken;
    }

    public void setReceiverToken(String receiverToken) {
        this.receiverToken = receiverToken;
    }
//    public ReceiverInfo getReceiverInfo() {
//        return receiverInfo;
//    }
//
//    public void setReceiverInfo(ReceiverInfo receiverInfo) {
//        this.receiverInfo = receiverInfo;
//    }

    /**
     * Output data field definitions.
     */
    public enum MessageType {

        MESSAGE(0),
        ACKNOWLEDGED(1),
        BROADCAST(2),
        GROUP(3),
        RESET(6),
        JOINED(7),
        EXIT(8),
        SERVER_ERROR(9);

        /**
         * The position of the field in the output data.
         */
        private final int index;

        private MessageType(int index) {
            this.index = index;
        }

        @JsonValue //if this annotation is not used then MESSAGE,JOINED,EXIT strings must be sent from client instead of 1,2,3 
        public int getIndex() {
            return this.index;
        }
    }
}
