package com.tgpms.auth.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Value("user.name")
    private String username;
    @Value("user.pass")
    private String password;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        redisTemplate.opsForValue().setIfAbsent("Init", "Success", 3, TimeUnit.MINUTES);
        System.out.println("项目初始化完成");
    }
}
