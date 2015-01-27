package com.superluli.spg.app.restexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple in-memory repository, it will also persist data to "db.json" to convenient test
 * 
 * @author luli
 *
 */
@Repository
@Replacable
public class MyModelRepository {

    private static final String DEFAULT_DB_FILE_NAME = "db.json";
    
    private String dbFileName;

    private Map<String, MyModel> models;

    private ObjectMapper mapper;

    public MyModelRepository(){
	this(DEFAULT_DB_FILE_NAME);
    }
    
    public MyModelRepository(String dbFileName){
	this.dbFileName = dbFileName;
	models = new HashMap<String, MyModel>();
	mapper = new ObjectMapper();
	reload();
    }

    public MyModel getModelById(String id) {

	return models.get(id);
    }

    public List<MyModel> getAllModels() {
	return new ArrayList<MyModel>(models.values());
    }

    public MyModel insertModel(MyModel model) {
	models.put(String.valueOf(model.getId()), model);
	rewrite();
	return model;
    }

    public MyModel updateModel(String id, MyModel model) {
	models.put(id, model);
	rewrite();
	return model;
    }

    public void deleteModel(String id) {
	models.remove(id);
	rewrite();
    }

    private void reload() {

	try {
	    File file = new File(System.getProperty("user.dir") + "/" + dbFileName);
	    if (!file.exists()) {
		file.createNewFile();
	    }
	    if (file.length() != 0) {
		models = mapper.readValue(new FileInputStream(file),
			new TypeReference<Map<String, MyModel>>() {
			});
	    }

	} catch (Exception e) {
	    throw new NestedWebappRuntimeException("reloading to DB error", e,
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    private void rewrite() {
	try {
	    PrintWriter out = new PrintWriter(new File(System.getProperty("user.dir")
		    + "/" + dbFileName));
	    out.print(mapper.writeValueAsString(models));
	    out.close();
	} catch (Exception e) {
	    throw new NestedWebappRuntimeException("writting to DB error", e,
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }
}
