package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.Message;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.MessageService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

/**
 * Created by emili on 05/12/2017.
 */

public class CallMessageModelImpl implements MessageService {

    private FirebaseHelper firebaseHelper;
    private DatabaseReference messageReference;
    private ChildEventListener childEventListener;
    private Context context;
    private CallMessageModelCallBack callMessageModelCallBack;


    public CallMessageModelImpl(Context context, FirebaseHelper firebaseHelper, CallMessageModelCallBack callMessageModelCallBack){
        this.firebaseHelper = firebaseHelper;
        this.context = context;
        this.callMessageModelCallBack = callMessageModelCallBack;
    }

    @Override
    public void sendMessage(Message messages) {

    }

    @Override
    public void checkLinked() {

    }

    @Override
    public void detachDataBaseReadListener() {

    }

    @Override
    public void attachDataBaseReadListerner() {
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message message = dataSnapshot.getValue(Message.class);

                    assert message != null;
                    if(message.getRecipientID().equals("ttt") && message.getSenderID().equals("")){
                        callMessageModelCallBack.getMessage(message);
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
            callMessageModelCallBack.onSuccessLoading();
            messageReference.addChildEventListener(childEventListener);
        }
    }
}
