package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.data.model.Message;

/**
 * Created by emili on 05/12/2017.
 */

public interface CallMessagePresenter {

    void checkLinked();
    void sendMessage(Message message);
    void setView(CallMessageView callMessageView);
    void detachDataBaseReadListener();
    void attachDataBaseReadListerner();
}
