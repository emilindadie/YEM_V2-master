package com.example.emili.friendbox.ui.mainActivity;

import android.content.Context;

import com.example.emili.friendbox.service.SignUpService;

import javax.inject.Inject;

/**
 * Created by emili on 30/11/2017.
 */

public class SignUpPresenterImpl implements SignUpPresenter , SignUpModelCallBack{

    SignUpService signUpService;
    static SignUpView signUpView;
    Context context;

    @Inject
    public SignUpPresenterImpl(SignUpService signUpService){
        //voir si probleme
        this.signUpService = signUpService;
    }

    @Override
    public void setView(SignUpView signUpView) {
    this.signUpView = signUpView;
    }

    @Override
    public void signUpUser(String firstName, String lastName, String email, String password, String adress, int code_postal, long number, boolean isDisabled, boolean hasLink) {
        signUpService.signUpUser(firstName, lastName, email, password, adress, code_postal, number, isDisabled, hasLink);
    }

    @Override
    public void onSuccessSignUp() {
        signUpView.showSuccessSignUp();
    }

    @Override
    public void onErrorSignUp(String errorMessage) {
    signUpView.showErrorSignUp(errorMessage);
    }
}
