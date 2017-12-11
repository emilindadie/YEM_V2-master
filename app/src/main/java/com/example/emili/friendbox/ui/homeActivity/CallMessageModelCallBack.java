package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.data.model.Message;

/**
 * Created by emili on 05/12/2017.
 */

public interface CallMessageModelCallBack {
    void getMessage(Message message);
    void getLinked(boolean linked);
    void onSuccessSending();
    void onErrorSending();
    void onSuccessLoading();
    void onErrorLoading();
}
