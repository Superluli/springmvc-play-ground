package com.superluli.spg.app.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.superluli.spg.app.configs.AppConfig;
import com.superluli.spg.app.configs.SecurityConfig;

@Component
public class PropertiesTest {

    @Autowired
    Environment env;

    @Autowired
    AppConfig appConfig;

    @Autowired
    SecurityConfig securityConfig;

    @PostConstruct
    public void test() {
	System.out.println(env.getProperty("app.name"));
	System.out.println(env.getProperty("security.level", "NOT_FOUND"));
	
	System.out.println(appConfig.getName());
	System.out.println(appConfig.owner.getName());
	System.out.println(securityConfig.getLevel());
    }
}
