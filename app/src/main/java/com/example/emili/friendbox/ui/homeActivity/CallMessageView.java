package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.data.model.Message;

/**
 * Created by emili on 05/12/2017.
 */

public interface CallMessageView {
    void showMessageList(Message message);
    void showLinked(boolean linked);
    void startLoading();
    void stopLoading();
    void showSuccessSending();
    void showErrorSending();
    void showSuccessLoading();
    void showErrorLoading();
}
