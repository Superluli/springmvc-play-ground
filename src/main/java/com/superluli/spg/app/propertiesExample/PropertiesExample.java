package com.superluli.spg.app.propertiesExample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.superluli.spg.app.configs.AppConfig;
import com.superluli.spg.app.configs.NBAAccountConfig;
import com.superluli.spg.app.configs.NestedConfig;

@Component
public class PropertiesExample {

    @Autowired
    Environment env;

    @Autowired
    AppConfig appConfig;

    @Autowired
    NestedConfig nestedConfig;

    @Autowired
    NBAAccountConfig nbaAccountConfig;
    
    @PostConstruct
    public void test() {
	/*
	 * get from ".properties" file via environment
	 */
	System.out.println(env.getProperty("app.name"));
	/*
	 * other properties file won't be merged into environment
	 */
	System.out.println(env.getProperty("security.level", "NOT_FOUND"));
	/*
	 * get from ".yaml" file via environment
	 */
	System.out.println(env.getProperty("league.teams[0].name"));
	/*
	 * get from ".properties" file via config bean
	 */
	System.out.println(appConfig);
	
	System.out.println(env.getProperty("nested.name"));
	System.out.println(nestedConfig);

	/*
	 * get from ".yaml" file via config bean
	 */
	System.out.println(nbaAccountConfig);
    }
}
