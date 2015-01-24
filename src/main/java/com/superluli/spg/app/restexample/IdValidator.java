package com.superluli.spg.app.restexample;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class IdValidator {
    
    public void validateId(String id) {
	if (id == null || id.isEmpty()) {
	    throw new NestedWebappRuntimeException("id required", HttpStatus.BAD_REQUEST);
	}
    }
}
