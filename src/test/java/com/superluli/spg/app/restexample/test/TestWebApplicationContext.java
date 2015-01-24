package com.superluli.spg.app.restexample.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.superluli.spg.app.restexample")
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestWebApplicationContext {
    
}
