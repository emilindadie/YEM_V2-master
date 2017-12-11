package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.data.model.User;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.NotificationService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by emili on 09/12/2017.
 */

public class NotificationModelImpl implements NotificationService{


    private FirebaseHelper firebaseHelper;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference notificationReference, userData;
    private ChildEventListener childEventListener;
    private NotificationModelCallBack notificationModelCallBack;
    private String userEmail ="";
    private Context context;

    @Inject
    public NotificationModelImpl(@ActivityContext Context context, FirebaseHelper firebaseHelper, NotificationModelCallBack notificationModelCallBack){
        this.firebaseHelper = firebaseHelper;
        firebaseAuth = firebaseHelper.getFirebaseAuth();
        notificationReference = firebaseHelper.getNotificationReference();
        this.notificationModelCallBack = notificationModelCallBack;
        userData = firebaseHelper.getUserDataReference(firebaseAuth.getCurrentUser());
        this.context = context;
    }


    @Override
    public void loadNotification() {

            //Get user data
            userData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    assert user != null;
                    userEmail = user.getEmail();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        //Get user notification

        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Notification notification = dataSnapshot.getValue(Notification.class);

                    if(notification.getRecipientEmail().equals(userEmail)){
                        notificationModelCallBack.getNotification(notification);
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
            notificationReference.addChildEventListener(childEventListener);
        }
    }
}
