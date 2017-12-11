package com.example.emili.friendbox.ui.editActivity;

import com.example.emili.friendbox.service.LinkViewService;

import javax.inject.Inject;

/**
 * Created by emili on 10/12/2017.
 */

public class LinkViewPresenterImpl implements LinkViewPresenter , LinkViewModelCallBack{

    LinkViewView linkViewView;
    LinkViewService linkViewService;

    @Inject
    public LinkViewPresenterImpl(LinkViewService linkViewService){
    this.linkViewService = linkViewService;
    }

    @Override
    public void setView(LinkViewView linkViewView) {
    this.linkViewView = linkViewView;
    }

    @Override
    public void loadLinkEmail() {
    linkViewService.loadLinkEmail();
    }

    @Override
    public void getEmailLink(boolean hasLink, String email) {
    linkViewView.showEmailLink(hasLink, email);
    }
}
