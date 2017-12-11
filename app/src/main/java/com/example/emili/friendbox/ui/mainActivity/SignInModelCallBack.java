package com.example.emili.friendbox.ui.mainActivity;

/**
 * Created by emili on 28/11/2017.
 */

public interface SignInModelCallBack {

    void onSuccessSignIn(boolean isDisabled);
    void onErrorSignIn(String errorMessage);
}
