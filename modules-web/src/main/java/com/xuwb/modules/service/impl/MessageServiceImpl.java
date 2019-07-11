package com.xuwb.modules.service.impl;

import com.xuwb.modules.service.MessageListenerAware;
import com.xuwb.modules.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void receiveAndHandlerMessage(MessageListenerAware listener, List<String> queueNames) {
        if (CollectionUtils.isEmpty(queueNames)){
            return;
        }
        for (String queueName:queueNames) {
            String message = null;
            message = (String) redisTemplate.opsForList().rightPop(queueName);
            if (message!=null) {
                listener.onMessage(message);
            }else {
                logger.info("no message in redis");
            }
        }
    }
}
