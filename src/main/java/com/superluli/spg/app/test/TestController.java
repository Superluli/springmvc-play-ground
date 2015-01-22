package com.superluli.spg.app.test;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	
	return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(value = "/greeting", method = RequestMethod.POST, consumes="application/json")
    public Greeting postGreeting(RequestEntity<Greeting> requestEntity) {
	throw new RuntimeException("LOL!");
	//return requestEntity.getBody();
    }
    
}
