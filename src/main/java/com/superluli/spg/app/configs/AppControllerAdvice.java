package com.superluli.spg.app.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
	return new ResponseEntity<Error>(new Error(HttpStatus.CONFLICT, e.getMessage()),
		HttpStatus.FORBIDDEN);
    }

    static class Error {
	HttpStatus status;
	public HttpStatus getStatus() {
	    return status;
	}

	public void setStatus(HttpStatus status) {
	    this.status = status;
	}

	public String getDesc() {
	    return desc;
	}

	public void setDesc(String desc) {
	    this.desc = desc;
	}

	String desc;

	public Error(){
	    
	}
	
	public Error(HttpStatus status, String desc) {
	    this.status = status;
	    this.desc = desc;
	}
    }
}
