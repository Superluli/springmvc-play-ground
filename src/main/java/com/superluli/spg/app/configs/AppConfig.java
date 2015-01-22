package com.superluli.spg.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    @Override
    public String toString() {
	return "AppConfig [name=" + name + ", owner=" + owner + "]";
    }

    private String name;
    public Owner owner;
    
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Owner {
	@Override
	public String toString() {
	    return "Owner [name=" + name + ", age=" + age + "]";
	}

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
