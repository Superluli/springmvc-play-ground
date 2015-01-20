package com.superluli.spg.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath:application.properties", prefix = "app")
public class AppConfig {

    private String name;
    public Owner owner;
    
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Owner {
	private String name;

	private int age;

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getAge() {
	    return age;
	}

	public void setAge(int age) {
	    this.age = age;
	}
    }

    public String getName() {

	return name;
    }

    public void setName(String name) {

	this.name = name;
    }
}
