package com.superluli.spg.app.restexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = MyModelController.class)
public class ExceptionHandlingAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NestedWebappRuntimeException.class)
    public final ResponseEntity<Error> handleException(NestedWebappRuntimeException e, WebRequest request) {
	HttpStatus httpStatus = e.getHttpStatus();
	return new ResponseEntity<Error>(new Error(httpStatus, e.getMessage()), httpStatus);
    }

    static class Error {

	HttpStatus status;

	String desc;

	public Error() {

	}

	public Error(HttpStatus status, String desc) {
	    this.status = status;
	    this.desc = desc;
	}

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
    }
}
