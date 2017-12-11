package com.example.emili.friendbox.ui.mainActivity;

import android.content.Context;

import com.example.emili.friendbox.service.SignInService;

import javax.inject.Inject;

/**
 * Created by emili on 19/11/2017.
 */

public class SignInPresenterImpl implements SignInPresenter, SignInModelCallBack{

    SignInService signInService;
    static SignInView signInView;

    @Inject
    public SignInPresenterImpl(SignInService signInService){
        this.signInService = signInService;
    }

    @Override
    public void signInUser(String email, String password) {
        signInService.signInUser(email, password);
    }

    @Override
    public void setView(SignInView signInView) {
        SignInPresenterImpl.signInView = signInView;
    }


    @Override
    public void onSuccessSignIn(boolean isDisabled) {
    signInView.goToHomeActivity(isDisabled);
    }

    @Override
    public void onErrorSignIn(String errorMessage) {
    signInView.showErrorSignIn(errorMessage);
    }
}
