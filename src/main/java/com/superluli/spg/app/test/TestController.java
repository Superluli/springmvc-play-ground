package com.superluli.spg.app.test;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
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
    public Greeting postGreeting(@RequestBody Greeting greeting) {
	test();
	return greeting;
    }
    
    public void test(){
	System.out.println("asda");
    }
    
}
