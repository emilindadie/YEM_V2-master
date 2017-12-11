package com.example.emili.friendbox.ui.mainActivity;

import android.content.Context;

/**
 * Created by emili on 30/11/2017.
 */

public interface SignUpPresenter {
    void setView(SignUpView signUpView);
    void signUpUser(String firstName, String lastName, String email, String password, String adress, int code_postal, long number, boolean isDisabled, boolean hasLink);
}
