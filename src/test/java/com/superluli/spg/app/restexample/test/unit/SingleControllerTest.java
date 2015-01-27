package com.superluli.spg.app.restexample.test.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Matchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superluli.spg.app.restexample.IdValidator;
import com.superluli.spg.app.restexample.MyModel;
import com.superluli.spg.app.restexample.MyModelController;
import com.superluli.spg.app.restexample.MyModelManager;
import com.superluli.spg.app.restexample.NestedWebappRuntimeException;
import com.superluli.spg.app.restexample.test.TestConstants;

public class SingleControllerTest {

    private static ObjectMapper jsonMapper;
    
    @Mock
    private MyModelManager mockManager;

    @Mock
    private IdValidator mockIdValidator;

    @InjectMocks
    private MyModelController mockController;

    private MockMvc mockMvc;
    
    @BeforeClass
    public static void beforClass() {
	jsonMapper = new ObjectMapper();
    }

    @Before
    public void before() {
	/*
	 * inject mockManager mockIdValidator into mockController, reset mock beans for each test
	 */
	MockitoAnnotations.initMocks(this);
	/*
	 * Load only instance of MyModelController, without other contexts. Since the exception
	 * handler was not wired, we need a HandlerExceptionResolver to help catch exceptions thrown
	 * by controller
	 */
	mockMvc = MockMvcBuilders.standaloneSetup(mockController)
		.setHandlerExceptionResolvers(new TestHandlerExceptionResolver()).build();
    }

    @Test
    public void testGetSuccess() throws Exception {
	
	String mockId = "xxx";
	MyModel added = new MyModel();
	added.setId(mockId);
	/*
	 * mock manager's behavior for this test : mockManager.get("xxx") -> added
	 */
	Mockito.when(mockManager.get(mockId)).thenReturn(added);
	
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
	model.setName("ball");
	model.setId("xxx");
	
	Mockito.when(mockManager.post(Matchers.any(MyModel.class))).thenReturn(model);
	
	mockMvc.perform(
		post("/resources").contentType(TestConstants.CONTENT_TYPE).content(
			jsonMapper.writeValueAsBytes(model)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is("xxx")))
			.andExpect(jsonPath("$.name", is("ball")));
    }

    @Test
    public void testDelete() throws Exception {

	mockMvc.perform(delete("/resources/{id}", "000")).andExpect(status().isNoContent());
    }
    
    static class TestHandlerExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
		HttpServletResponse response, Object handler, Exception ex) {

	    if (ex instanceof NestedWebappRuntimeException) {
		NestedWebappRuntimeException casted = (NestedWebappRuntimeException) ex;
		response.setStatus(casted.getHttpStatus().value());
		try {
		    response.getWriter().write(casted.getMessage());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    return new ModelAndView();
	}
    }
}
