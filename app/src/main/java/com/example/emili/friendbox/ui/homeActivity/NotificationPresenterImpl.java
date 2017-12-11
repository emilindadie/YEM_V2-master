package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.service.NotificationService;

import javax.inject.Inject;

/**
 * Created by emili on 09/12/2017.
 */

public class NotificationPresenterImpl implements NotificationPresenter , NotificationModelCallBack{

    private static NotificationView notificationView;
    private NotificationService notificationService;

    @Inject
    public NotificationPresenterImpl(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Override
    public void setView(NotificationView notificationView) {
    NotificationPresenterImpl.notificationView = notificationView;
    }

    @Override
    public void loadNotification() {
    notificationService.loadNotification();
    }

    @Override
    public void getNotification(Notification notification) {
    notificationView.showNotification(notification);
    }
}
