package com.example.emili.friendbox.ui.editActivity;

/**
 * Created by asus on 07/12/2017.
 */

public interface LinkEditModelCallBack {
    void getAskResult(boolean emailLinkExist, boolean recipientHasLink, boolean isProfilEquals,  boolean heSendMeNotification, boolean isAlreadyNotified, boolean isNotified);
}
