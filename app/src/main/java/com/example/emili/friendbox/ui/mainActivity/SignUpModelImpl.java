package com.example.emili.friendbox.ui.mainActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.model.User;
import com.example.emili.friendbox.di.ActivityContext;
import com.example.emili.friendbox.service.SignUpService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

/**
 * Created by emili on 30/11/2017.
 */

public class SignUpModelImpl implements SignUpService {

    private FirebaseHelper firebaseHelper;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private DatabaseReference userDatabaseReference;
    SignUpModelCallBack signUpModelCallBack;
    Handler handler;

    @Inject
    public SignUpModelImpl(@ActivityContext Context context, FirebaseHelper firebaseHelper, SignUpModelCallBack signUpModelCallBack){
        this.context = context;
        this.signUpModelCallBack = signUpModelCallBack;
        this.firebaseHelper = firebaseHelper;
        firebaseAuth = firebaseHelper.getFirebaseAuth();
        handler = new Handler();
    }

    @Override
    public void signUpUser(final String firstName, final String lastName, final String email, final String password, final String adress, final int code_postal, final long number, final boolean isDisabled, final boolean hasLink) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    onAuthSuccess(task.getResult().getUser(), firstName, lastName, email, adress, code_postal, number, isDisabled, hasLink);
                }
                else {
                    Log.w("LOGTAG", "signInWithEmail:failed", task.getException());
                    signUpModelCallBack.onErrorSignUp(task.getException().getMessage());
                }
            }
        });
    }

    private void onAuthSuccess(final FirebaseUser user, final String firstName, final String lastName, final String email, final String adress, final int code_postal, final long number, final boolean isDisabled, final boolean hasLink) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                writeNewUser(user, user.getUid(), firstName, lastName, email,adress, code_postal, number, isDisabled, hasLink);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        signUpModelCallBack.onSuccessSignUp();
                    }
                });
            }
        });
        thread.start();
    }

    private void writeNewUser(FirebaseUser firebaseUser, final String userId, final String firstName, final String lastName, final String email, String adress, int code_postal, long number, boolean isDisabled, boolean hasLink) {
        userDatabaseReference = firebaseHelper.getUserDataReference(firebaseUser);
        User user = new User(userId, firstName, lastName, email, adress, code_postal, number, isDisabled, hasLink);
        userDatabaseReference.setValue(user);
    }
}
