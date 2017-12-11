package com.example.emili.friendbox.ui.editActivity;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.data.model.User;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.LinkEditService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by asus on 07/12/2017.
 */

public class LinkEditModelImpl implements LinkEditService {

    private FirebaseHelper firebaseHelper;
    private DatabaseReference userData;
    private FirebaseAuth firebaseAuth;
    private LinkEditModelCallBack linkEditModelCallBack;
    private android.os.Handler hander;
    private ChildEventListener childEventListener;
    private DatabaseReference allUsers, linkNotificationReferance;
    private boolean isProfilEquals = false;
    private boolean isNotified = false;
    private boolean ifRecipientHasLink = false;
    private boolean isAlreadyNotified = false;
    private boolean heSendMeNotification = false;
    private boolean emailExist = false;
    private boolean ifSenderIsDisabled , ifRecipeintIsDisabled;
    private String senderFirstName="";
    private String senderEmail="";
    private String recipientEmail="";


    @Inject
    public LinkEditModelImpl(@ActivityContext Context context, FirebaseHelper firebaseHelper, LinkEditModelCallBack linkEditModelCallBack){
        this.firebaseHelper = firebaseHelper;
        this.firebaseAuth = firebaseHelper.getFirebaseAuth();
        this.linkEditModelCallBack = linkEditModelCallBack;
        userData = firebaseHelper.getUserDataReference(firebaseAuth.getCurrentUser());
        allUsers = firebaseHelper.getAllUsersDataReference();
        hander = new android.os.Handler();
    }


    @Override
    public void sendAskLinkEmailRequest(final String email) {
        getUserInfo();
        getRecipientInfo(email);

        if(ifSenderIsDisabled == ifRecipeintIsDisabled){
            isProfilEquals = true;
        }

        if(!emailExist && !ifRecipientHasLink && !isProfilEquals){
            checkIfNotificationExist();
            if (!heSendMeNotification && !isAlreadyNotified) {
                sendNotification();
            }else {
                linkEditModelCallBack.getAskResult(emailExist, ifRecipientHasLink, isProfilEquals, heSendMeNotification, isAlreadyNotified, isNotified);
            }

        }else {
            linkEditModelCallBack.getAskResult(emailExist,ifRecipientHasLink, isProfilEquals, heSendMeNotification, isAlreadyNotified, isNotified);
        }
    }

    private void sendNotification() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        Notification notification = new Notification(senderEmail, recipientEmail, "linkRequest", senderFirstName + " souahite relier son compte au votre", currentDate, false);
        linkNotificationReferance.push().setValue(notification);
        isNotified = true;
        linkEditModelCallBack.getAskResult(emailExist, ifRecipientHasLink, isProfilEquals, heSendMeNotification, isAlreadyNotified, isNotified);
    }

    private void getUserInfo(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                userData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        assert user != null;
                        ifSenderIsDisabled = user.isDesabled();
                        senderFirstName = user.getFirstName();
                        senderEmail = user.getEmail();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        thread.start();
    }
    private void getRecipientInfo(final String email){
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.exists() && !emailExist){
                        User user = dataSnapshot.getValue(User.class);
                        assert user != null;
                        if(user.getEmail().equals(email)){
                            ifRecipeintIsDisabled = user.isDesabled();
                            recipientEmail = user.getEmail();
                            ifRecipientHasLink = user.isHasLink();
                            emailExist = true;
                        }
                    }
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            allUsers.addChildEventListener(childEventListener);
        }
    }
    private void checkIfNotificationExist(){

        linkNotificationReferance = firebaseHelper.getNotificationReference();
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.exists() && !heSendMeNotification && isAlreadyNotified){
                        Notification notification = dataSnapshot.getValue(Notification.class);

                        if(notification.getRecipientEmail() == senderEmail){
                            heSendMeNotification = true;

                        }else if(notification.getSenderEmail() == senderEmail){
                            isAlreadyNotified = true;
                        }
                    }
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            linkNotificationReferance.addChildEventListener(childEventListener);
        }
    }
}
