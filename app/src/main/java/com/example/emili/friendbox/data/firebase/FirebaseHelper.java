package com.example.emili.friendbox.data.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

/**
 * Created by emili on 28/11/2017.
 */

@Singleton
public class FirebaseHelper {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public FirebaseHelper(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }

    public DatabaseReference getUserDataReference(FirebaseUser firebaseUser){
        if(firebaseUser != null){
            return  databaseReference.child("users").child(firebaseUser.getUid());
        }
        return null;
    }



    public DatabaseReference getAllUsersDataReference(){
            return  databaseReference.child("users");
    }

    public DatabaseReference getUserMessagerieReference(FirebaseUser firebaseUser){
        if(firebaseUser != null){
            return  databaseReference.child("messages");
        }
        return null;
    }

    public DatabaseReference getNotificationReference(){
        return  databaseReference.child("notification");
    }
}
