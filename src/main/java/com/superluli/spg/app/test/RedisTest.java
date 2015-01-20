package com.superluli.spg.app.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
    
    @Autowired
    RedisTemplate<String, String> template;
    
    @PostConstruct
    public void test(){
	System.out.println(template.toString());
    }
}
