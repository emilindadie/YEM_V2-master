package com.example.emili.friendbox.ui.editActivity;

import android.content.Context;
import android.os.Handler;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.Link;
import com.example.emili.friendbox.data.model.User;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.LinkViewService;
import com.example.emili.friendbox.ui.homeActivity.CallMessageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by emili on 10/12/2017.
 */

public class LinkViewModelImpl implements LinkViewService{

    LinkViewModelCallBack linkViewModelCallBack;
    FirebaseHelper firebaseHelper;
    DatabaseReference userInfo, userLink;
    FirebaseAuth firebaseAuth;
    boolean hasLink = false;
    Handler handler;

    @Inject
    public LinkViewModelImpl(@ActivityContext Context context, FirebaseHelper firebaseHelper, LinkViewModelCallBack linkViewModelCallBack){
        this.linkViewModelCallBack = linkViewModelCallBack;
        this.firebaseHelper = firebaseHelper;
        this.firebaseAuth = firebaseHelper.getFirebaseAuth();
        userInfo = firebaseHelper.getUserDataReference(firebaseAuth.getCurrentUser());
        userLink = firebaseHelper.getUserDataReference(firebaseAuth.getCurrentUser()).child("link");
        handler = new Handler();
    }

    @Override
    public void loadLinkEmail() {

        getUserData();
        if(hasLink){
            getUserLink();
        }
        else {
            linkViewModelCallBack.getEmailLink(false, null);
        }
    }

    private void getUserLink() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                userLink.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){
                            Link link = dataSnapshot.getValue(Link.class);
                            assert link != null;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                            linkViewModelCallBack.getEmailLink(true, link.getLinkEmail());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        thread.start();
    }

    public void getUserData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                userInfo.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);
                        hasLink = user.isHasLink();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        thread.start();
    }
}