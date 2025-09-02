package com.himanshu.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get(String key, Class<T> classEntity) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o==null) {return null;}
            else{ return classEntity.cast(o);}

        } catch (Exception e) {
            log.error("exception", e);
            return null;
        }


    }

    public void set(String key, Object o, long ttl) {
        try {

            redisTemplate.opsForValue().set(key, o , Duration.ofSeconds(ttl));

        } catch (Exception e) {
            log.error("exception", e);

        }

    }
}
