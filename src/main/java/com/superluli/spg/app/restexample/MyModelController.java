package com.superluli.spg.app.restexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resources")
public class MyModelController {

    @Autowired
    private MyModelManager manager;

    @Autowired
    private IdValidator validator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MyModel get(@PathVariable String id) {

	validator.validateId(id);

	MyModel result = manager.get(id);

	if (result == null) {
	    throw new NestedWebappRuntimeException("resource with id " + id + " was not found",
		    HttpStatus.NOT_FOUND);
	}
	return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MyModel> list() {

	return manager.list();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public MyModel post(@RequestBody MyModel model) {

	return manager.post(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public MyModel put(@PathVariable String id, @RequestBody MyModel model) {

	validator.validateId(id);
	MyModel existingModel = manager.get(id);
	if(existingModel == null){
	    throw new NestedWebappRuntimeException("resource with id " + id + " was not found",
		    HttpStatus.NOT_FOUND);
	}
	return manager.put(id, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable String id) {

	manager.delete(id);
	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    public MyModelManager getManager() {
        return manager;
    }

    public void setManager(MyModelManager manager) {
        this.manager = manager;
    }

    public IdValidator getValidator() {
        return validator;
    }

    public void setValidator(IdValidator validator) {
        this.validator = validator;
    }

}
