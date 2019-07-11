package com.xuwb.modules.service;

import java.util.List;

public interface MessageService {
    void receiveAndHandlerMessage(MessageListenerAware messageListenerAware, List<String> queueNames);
}
