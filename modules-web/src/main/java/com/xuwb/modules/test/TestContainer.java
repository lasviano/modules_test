package com.xuwb.modules.test;

import com.xuwb.modules.service.MessageListenerAware;
import com.xuwb.modules.service.MessageService;

import java.util.List;

public class TestContainer {

    private MessageListenerAware listener;
    private MessageConsumer consumer;
    private MessageService messageService;
    private List<String> queueNames;

    public void setQueueNames(List<String> queueNames) {
        this.queueNames = queueNames;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void setListener(MessageListenerAware listener) {
        this.listener = listener;
    }

    public void myLaunch(){
        System.out.println(": run TestContainer.myLaunch");
        consumer = new MessageConsumer(messageService,listener,queueNames);
        new Thread(consumer).start();
    }
}
