package com.superluli.spg.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations = { "classpath:/nested.properties" }, merge = true, prefix = "nested")
public class NestedConfig {

    @Override
    public String toString() {
	return "NestedConfig [name=" + name + ", address=" + address + "]";
    }

    private String name;
    private Address address;
    
    public static class Address {
	@Override
	public String toString() {
	    return "Address [number=" + number + ", street=" + street + ", city=" + city + ", zip="
		    + zip + "]";
	}

	private String number;

	public String getNumber() {
	    return number;
	}

	public void setNumber(String number) {
	    this.number = number;
	}

	public String getStreet() {
	    return street;
	}

	public void setStreet(String street) {
	    this.street = street;
	}

	public String getCity() {
	    return city;
	}

	public void setCity(String city) {
	    this.city = city;
	}

	public int getZip() {
	    return zip;
	}

	public void setZip(int zip) {
	    this.zip = zip;
	}

	private String street;
	private String city;
	private int zip;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

}
