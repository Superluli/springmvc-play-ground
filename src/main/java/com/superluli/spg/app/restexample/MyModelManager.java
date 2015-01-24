package com.superluli.spg.app.restexample;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyModelManager {

    @Autowired
    private MyModelRepository repo;

    public MyModel get(String id) {
	return repo.getModelById(id);
    }

    public List<MyModel> list() {
	return repo.getAllModels();
    }

    public MyModel post(MyModel model) {
	model.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	return repo.insertModel(model);
    }

    public MyModel put(String id, MyModel model) {

	return repo.updateModel(id, model);
    }

    public void delete(String id) {
	repo.deleteModel(id);
    }
}
