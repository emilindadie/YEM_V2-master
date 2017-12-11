package com.example.emili.friendbox.ui.editActivity;

/**
 * Created by asus on 07/12/2017.
 */

public interface LinkEditPresenter {
    void sendAskLinkEmailRequest(String email);
    void setView(LinkEditView linkEditView);
}
