package com.example.emili.friendbox.service;

import android.content.Context;
import android.icu.text.StringPrepParseException;

/**
 * Created by emili on 28/11/2017.
 */

public interface SignInService {
    void signInUser(String email, String password);
}
