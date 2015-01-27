package com.superluli.spg.app.restexample.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superluli.spg.app.restexample.MyModel;
import com.superluli.spg.app.restexample.MyModelController;
import com.superluli.spg.app.restexample.MyModelManager;
import com.superluli.spg.app.restexample.MyModelRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = TestWebApplicationContext.class)
@DirtiesContext
public class IntegrationWithMockTest {

    private static ObjectMapper jsonMapper;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private MyModelRepository mockRepository;

    @InjectMocks
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
	/*
	 * tests are "integration tests" and therefore require full startup in
	 * the same way as a production application
	 */
	mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	/*
	 * replace real manager to mockManager
	 */
	wac.getBean(MyModelController.class).setManager(manager);
    }

    @Test
    public void testGetSuccess() throws Exception {
	String mockId = "xxx";
	MyModel added = new MyModel();
	added.setId(mockId);
	Mockito.when(mockRepository.getModelById(mockId)).thenReturn(added);
	mockMvc.perform(get("/resources/{id}", mockId)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is("xxx")));
    }

    @Test
    public void testGetNotFound() throws Exception {
	mockMvc.perform(get("/resources/{id}", "000")).andExpect(status().isNotFound());
    }

    @Test
    public void testPostSuccess() throws Exception {

	MyModel model = new MyModel();
	model.setId("123");
	model.setName("ball");

	Mockito.when(mockRepository.insertModel(Matchers.any(MyModel.class))).thenReturn(model);

	MyModel m = manager.post(new MyModel());
	System.out.println(m);
	mockMvc.perform(
		post("/resources").contentType("application/json").content(
			jsonMapper.writeValueAsBytes(model))).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is("123"))).andExpect(jsonPath("$.name", is("ball")));

    }

    @Test
    public void testDelete() throws Exception {

	mockMvc.perform(delete("/resources/{id}", "000")).andExpect(status().isNoContent());
    }

}
