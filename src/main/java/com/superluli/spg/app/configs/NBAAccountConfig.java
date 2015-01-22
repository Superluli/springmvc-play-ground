package com.superluli.spg.app.configs;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = "classpath : application.yaml")
public class NBAAccountConfig {

    @Override
    public String toString() {
	return "NBAAccountConfig [watcher=" + watcher + ", league=" + league + ", app=" + app + "]";
    }

    public Watcher getWatcher() {
	return watcher;
    }

    public void setWatcher(Watcher watcher) {
	this.watcher = watcher;
    }

    public League getLeague() {
	return league;
    }

    public void setLeague(League league) {
	this.league = league;
    }

    private Watcher watcher;
    private League league;
    private String app;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public static class Watcher {
	@Override
	public String toString() {
	    return "Watcher [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

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

	public Address getAddress() {
	    return address;
	}

	public void setAddress(Address address) {
	    this.address = address;
	}

	private String name;
	private int age;
	private Address address;

	public static class Address {

	}
    }

    public static class League {
	@Override
	public String toString() {
	    return "League [name=" + name + ", teams=" + teams + "]";
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public List<Team> getTeams() {
	    return teams;
	}

	public void setTeams(List<Team> teams) {
	    this.teams = teams;
	}

	private String name;
	private List<Team> teams;

	public static class Team {
	    @Override
	    public String toString() {
		return "Team [name=" + name + "]";
	    }

	    private String name;

	    public String getName() {
		return name;
	    }

	    public void setName(String name) {
		this.name = name;
	    }
	}
    }
}
