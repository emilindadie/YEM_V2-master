package com.example.emili.friendbox.ui.editActivity;

import com.example.emili.friendbox.service.LinkEditService;

import javax.inject.Inject;

/**
 * Created by asus on 07/12/2017.
 */

public class LinkEditPresenterImpl implements LinkEditPresenter , LinkEditModelCallBack {


    LinkEditService linkEditService;
    static LinkEditView linkEditView;

    @Inject
    public LinkEditPresenterImpl(LinkEditService linkEditService){

        this.linkEditService = linkEditService;
    }

    @Override
    public void sendAskLinkEmailRequest(String email) {
        linkEditService.sendAskLinkEmailRequest(email);
    }


    @Override
    public void setView(LinkEditView linkEditView) {
        LinkEditPresenterImpl.linkEditView = linkEditView;
    }


    @Override
    public void getAskResult(boolean emailLinkExist, boolean recipientHasLink, boolean isProfilEquals, boolean heSendMeNotification, boolean isAlreadyNotified, boolean isNotified) {
        LinkEditPresenterImpl.linkEditView.showAskLinkEmailResult(emailLinkExist, recipientHasLink, isProfilEquals, heSendMeNotification, isAlreadyNotified, isNotified);
    }

}
