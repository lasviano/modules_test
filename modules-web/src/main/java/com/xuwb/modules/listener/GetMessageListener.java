package com.xuwb.modules.listener;

import com.xuwb.modules.service.MessageListenerAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GetMessageListener implements MessageListenerAware {

    private static final Logger logger = LoggerFactory.getLogger(GetMessageListener.class);

    @Override
    public void onMessage(String message){
        logger.info("================get the message successful===================");
        logger.info("message:    "+message);
    }
}
