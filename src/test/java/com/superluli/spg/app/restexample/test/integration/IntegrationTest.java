package com.superluli.spg.app.restexample.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superluli.spg.app.restexample.MyModel;
import com.superluli.spg.app.restexample.test.TestConstants;
import com.superluli.spg.app.restexample.test.TestWebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = TestWebApplicationContext.class)
public class IntegrationTest {

    private static ObjectMapper jsonMapper;

    @Autowired
    private WebApplicationContext wac;

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
    }

    @Test
    public void testCRUD() throws Exception {

	/*
	 * create sample resource
	 */
	MyModel model = new MyModel();
	model.setName("ball");

	/*
	 * post
	 */
	MvcResult postResult = mockMvc
		.perform(
			post("/resources").contentType(TestConstants.CONTENT_TYPE).content(
				jsonMapper.writeValueAsBytes(model))).andExpect(status().isOk())
		.andReturn();

	MyModel resultModel = jsonMapper.readValue(
		postResult.getResponse().getContentAsByteArray(), MyModel.class);

	String id = resultModel.getId();

	/*
	 * get
	 */
	mockMvc.perform(get("/resources/{id}", id).contentType(TestConstants.CONTENT_TYPE))
		.andExpect(status().isOk())
		.andExpect(content().bytes(jsonMapper.writeValueAsBytes(resultModel)));
	/*
	 * update
	 */
	resultModel.set("another key", "another value");
	mockMvc.perform(
		put("/resources/{id}", id).contentType(TestConstants.CONTENT_TYPE).content(
			jsonMapper.writeValueAsBytes(resultModel))).andExpect(status().isOk())
		.andExpect(content().bytes(jsonMapper.writeValueAsBytes(resultModel)));
	/*
	 * delete
	 */
	mockMvc.perform(delete("/resources/{id}", id)).andExpect(status().isNoContent());

	/*
	 * get should return not found
	 */
	mockMvc.perform(get("/resources/{id}", id).contentType(TestConstants.CONTENT_TYPE)).andExpect(
		status().isNotFound());
    }
}
