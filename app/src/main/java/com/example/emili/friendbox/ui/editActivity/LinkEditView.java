package com.example.emili.friendbox.ui.editActivity;

/**
 * Created by asus on 07/12/2017.
 */

public interface LinkEditView {
    void showOnSuccesAskSending();
    void showOnErrorEmailNotFound();
    void showAskLinkEmailResult(boolean emailLinkExist, boolean recipientHasLink, boolean isProfilEquals, boolean heSendMeNotification, boolean isAlreadyNotified, boolean isNotified);
}
