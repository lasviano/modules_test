package com.xuwb.modules.configuration;

import com.xuwb.modules.listener.GetMessageListener;
import com.xuwb.modules.service.MessageListenerAware;
import com.xuwb.modules.service.MessageService;
import com.xuwb.modules.test.TestContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean(initMethod = "myLaunch")
    public TestContainer testContainer(MessageService messageService,MessageListenerAware listener,@Value("messageQueue") String queueNames){
        TestContainer testContainer = new TestContainer();
        testContainer.setMessageService(messageService);
        testContainer.setListener(listener);
        testContainer.setQueueNames(Arrays.asList(new String[]{queueNames}));
        return testContainer;
    }

}
