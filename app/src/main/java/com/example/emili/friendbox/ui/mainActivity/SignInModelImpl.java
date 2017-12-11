package com.example.emili.friendbox.ui.mainActivity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.User;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.SignInService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by emili on 28/11/2017.
 */

public class SignInModelImpl implements SignInService {

    FirebaseAuth firebaseAuth;
    Context context;
    FirebaseHelper firebaseHelper;
    SignInModelCallBack signInModelCallBack;
    DatabaseReference userData;

    @Inject
    public SignInModelImpl(@ActivityContext Context context, FirebaseHelper firebaseHelper, SignInModelCallBack signInModelCallBack){
        this.context = context;
        this.firebaseHelper = firebaseHelper;
        this.signInModelCallBack = signInModelCallBack;
        firebaseAuth = firebaseHelper.getFirebaseAuth();
    }

    @Override
    public void signInUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    userData = firebaseHelper.getUserDataReference(user);
                    userData.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            assert user != null;
                                signInModelCallBack.onSuccessSignIn(user.isDesabled());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Log.w("LOGTAG", "signInWithEmail:failed", task.getException());
                    signInModelCallBack.onErrorSignIn(task.getException().getMessage());
                }
            }
        });
    }
}
