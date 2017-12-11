package com.example.emili.friendbox.service;

import android.content.Context;

/**
 * Created by emili on 30/11/2017.
 */

public interface SignUpService {
    void signUpUser(String firstName, String lastName, String email, String password, String adress, int code_postal, long number, boolean isDisabled, boolean hasLink);
}
