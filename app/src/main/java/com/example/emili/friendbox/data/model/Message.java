package com.example.emili.friendbox.data.model;

/**
 * Created by emili on 05/12/2017.
 */

public class Message {

    String senderID;
    String recipientID;

    public Message(){

    }

    public Message(String senderID, String recipientID){
        this.senderID = senderID;
        this.recipientID = recipientID;
    }

    public String getSenderID(){
        return senderID;
    }

    public String getRecipientID(){
        return recipientID;
    }
}
