package com.superluli.spg.app.test;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Greeting {

    private long id;
    private String content;
    private Map<String, Object> fields;

    public Greeting() {
	fields = new HashMap<String, Object>();
    }

    public Greeting(long id, String content) {
	this();
	this.id = id;
	this.content = content;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }
    
    public Object getValue(String key){
	return fields.get(key);
    }
    
    @JsonAnyGetter
    public Map<String,Object> any() {
        return fields;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        fields.put(name, value);
    }
}
