package com.xuwb.modules.controller;

import com.xuwb.modules.dto.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/{messageId}")
    public GeneralResponse<String> redisTest(@PathVariable("messageId") Integer messageId){
        String message = "message"+messageId;
        redisTemplate.opsForList().leftPush("messageQueue",message);
        String res = "res:  "+redisTemplate.opsForList().index("messageQueue",0);
        return new GeneralResponse<>(res);
    }

    @GetMapping("/hello")
    public String hello() throws IOException {
        String defaultSqlResourcePattern = "classpath*:sql/*.sql";

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(defaultSqlResourcePattern);

        for (Resource resource:resources){
            System.out.println(resource);
        }

        return "hello";
    }
}
