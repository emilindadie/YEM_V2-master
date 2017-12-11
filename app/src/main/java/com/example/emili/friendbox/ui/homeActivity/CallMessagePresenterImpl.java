package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.data.model.Message;
import com.example.emili.friendbox.service.MessageService;

/**
 * Created by emili on 05/12/2017.
 */

public class CallMessagePresenterImpl implements CallMessagePresenter , CallMessageModelCallBack{


    MessageService messageService;
    static CallMessageView callMessageView;

    public CallMessagePresenterImpl(MessageService messageService){
        this.messageService = messageService;
    }

    @Override
    public void checkLinked() {

    }

    @Override
    public void sendMessage(Message message) {
    messageService.sendMessage(message);
    }

    @Override
    public void setView(CallMessageView callMessageView) {
    CallMessagePresenterImpl.callMessageView = callMessageView;
    }

    @Override
    public void detachDataBaseReadListener() {
    messageService.detachDataBaseReadListener();
    }

    @Override
    public void attachDataBaseReadListerner() {
    messageService.attachDataBaseReadListerner();
    }

    @Override
    public void getMessage(Message message) {

    }

    @Override
    public void getLinked(boolean linked) {

    }

    @Override
    public void onSuccessSending() {

    }

    @Override
    public void onErrorSending() {

    }

    @Override
    public void onSuccessLoading() {

    }

    @Override
    public void onErrorLoading() {

    }
}
