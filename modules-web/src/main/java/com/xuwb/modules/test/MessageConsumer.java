package com.xuwb.modules.test;

import com.xuwb.modules.service.MessageListenerAware;
import com.xuwb.modules.service.MessageService;

import java.util.List;

public class MessageConsumer implements Runnable{

    private MessageListenerAware listener;
    private MessageService messageService;
    private List<String> queueNames;

    public MessageConsumer(MessageService messageService,MessageListenerAware listener,List<String> queueNames){
        this.listener = listener;
        this.messageService = messageService;
        this.queueNames = queueNames;
    }

    @Override
    public void run() {
        messageService.receiveAndHandlerMessage(listener,queueNames);
    }
}
