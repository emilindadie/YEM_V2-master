package com.example.emili.friendbox.service;

import com.example.emili.friendbox.data.model.Message;

/**
 * Created by emili on 05/12/2017.
 */

public interface MessageService {
    void sendMessage(Message messages);
    void checkLinked();
    void detachDataBaseReadListener();
    void attachDataBaseReadListerner();
}
