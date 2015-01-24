package com.superluli.spg.app.restexample.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superluli.spg.app.ApplicationContext;
import com.superluli.spg.app.restexample.IdValidator;
import com.superluli.spg.app.restexample.MyModel;
import com.superluli.spg.app.restexample.MyModelController;
import com.superluli.spg.app.restexample.MyModelManager;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = TestWebApplicationContext.class)
public class MyModelControllerTest {
    
    private static ObjectMapper jsonMapper;

    @Autowired
    private WebApplicationContext wac;
    
    @Mock
    private MyModelManager manager;

    private MockMvc mockMvc;

    @BeforeClass
    public static void beforClass() {
	jsonMapper = new ObjectMapper();
    }

    @Before
    public void before() {
	/*
	 * reset mock beans for each test
	 */
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	wac.getBean(MyModelController.class).setManager(manager);
    }

    @Test
    public void testGetSuccess() throws Exception {
	String mockId = "xxx";
	MyModel added = new MyModel();
	added.setId(mockId);
	Mockito.when(manager.get(mockId)).thenReturn(added);
	mockMvc.perform(get("/resources/{id}", mockId)).andExpect(status().isOk())
		.andExpect(content().bytes(jsonMapper.writeValueAsBytes(added)));
    }

    @Test
    public void testGetNotFound() throws Exception {
	mockMvc.perform(get("/resources/{id}", "000")).andExpect(status().isNotFound());
    }

    @Test
    public void testPostSuccess() throws Exception {

	MyModel model = new MyModel();
	model.setName("ball");

	mockMvc.perform(
		post("/resources").contentType("application/json").content(
			jsonMapper.writeValueAsBytes(model))).andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

	mockMvc.perform(delete("/resources/{id}", "000")).andExpect(status().isNoContent());
    }

}
