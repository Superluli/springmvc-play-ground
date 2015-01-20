package com.superluli.spg.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath:security.properties", prefix = "security")
public class SecurityConfig {

    private String level;

    public String getLevel() {

	return level;
    }

    public void setLevel(String level) {

	this.level = level;
    }
}
