package com.example.emili.friendbox.data.model;

/**
 * Created by emili on 07/12/2017.
 */

public class Notification {


    private String notificationType;
    private String senderEmail;
    private String recipientEmail;
    private String notificationContent;
    private String notificationDate;
    private boolean isMarked;

    public Notification(){

    }


    public Notification(String senderEmail, String recipientEmail, String notificationType, String notificationContent, String notificationDate, boolean isMarked){

        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.notificationType = notificationType;
        this.notificationContent = notificationContent;
        this.notificationDate = notificationDate;
        this.isMarked = isMarked;
    }


    public  String getNotificationType(){
        return notificationType;
    }
    public  String getNotificationContent(){
        return notificationContent;
    }
    public  String getNotificationDate(){
        return notificationDate;
    }

    public boolean isMarked(){
        return isMarked;
    }

    public String getSenderEmail(){
        return senderEmail;
    }
    public String getRecipientEmail(){
        return recipientEmail;
    }

}
