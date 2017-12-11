package com.example.emili.friendbox.ui.mainActivity;

import android.content.Context;

import com.example.emili.friendbox.di.PerActivity;

/**
 * Created by emili on 19/11/2017.
 */


public interface SignInPresenter {
    void signInUser(String email, String password);
    void setView(SignInView signInView);
}
