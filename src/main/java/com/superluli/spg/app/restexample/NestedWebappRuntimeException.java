package com.superluli.spg.app.restexample;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

public class NestedWebappRuntimeException extends NestedRuntimeException {

    private static final long serialVersionUID = 6403517901257779884L;
    
    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
	return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
	this.httpStatus = httpStatus;
    }

    public NestedWebappRuntimeException(String msg, HttpStatus httpStatus) {
	super(msg);
	setHttpStatus(httpStatus);
    }

    public NestedWebappRuntimeException(String msg, Throwable cause, HttpStatus httpStatus) {
	super(msg, cause);
	setHttpStatus(httpStatus);
    }

}
