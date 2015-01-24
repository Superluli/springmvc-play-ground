package com.superluli.spg.app.restexample;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class MyModel {

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MyModel other = (MyModel) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    private String id;
    private String name;
    private Map<String, Object> fields;

    public MyModel() {
	fields = new HashMap<String, Object>();
    }

    public MyModel(String id, String name) {
	this();
	this.id = id;
	this.name = name;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String content) {
	this.name = content;
    }

    public Object getValue(String key) {
	return fields.get(key);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
	return fields;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
	fields.put(name, value);
    }
}
