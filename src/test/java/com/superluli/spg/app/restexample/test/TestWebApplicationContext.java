package com.superluli.spg.app.restexample.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.superluli.spg.app.restexample.MyModelRepository;
import com.superluli.spg.app.restexample.Replacable;

@Configuration
/*
 * exclude real MyModelRepository, we need a fake one
 */
@ComponentScan(basePackages = "com.superluli.spg.app.restexample", excludeFilters = @ComponentScan.Filter(value = Replacable.class))
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestWebApplicationContext {

    @Bean
    public MyModelRepository getMyModelRepository(){
	return new MyModelRepository("test.db");
    }
}
