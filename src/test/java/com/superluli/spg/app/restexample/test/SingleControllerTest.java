package com.superluli.spg.app.restexample.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.superluli.spg.app.restexample.IdValidator;
import com.superluli.spg.app.restexample.MyModelController;
import com.superluli.spg.app.restexample.MyModelManager;
import com.superluli.spg.app.restexample.NestedWebappRuntimeException;

public class SingleControllerTest {

    @Mock
    private MyModelManager mockManager;

    @Mock
    private IdValidator mockIdValidator;

    @InjectMocks
    private MyModelController mockController;

    private MockMvc mockMvc;

    @Before
    public void before() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(mockController)
		.setHandlerExceptionResolvers(new TestHandlerExceptionResolver()).build();
    }

    @Test
    public void testGetNotFound() throws Exception {
	mockMvc.perform(get("/resources/{id}", "000")).andExpect(status().isNotFound());
    }

    static class TestHandlerExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
		HttpServletResponse response, Object handler, Exception ex) {
	    
	    if(ex instanceof NestedWebappRuntimeException){
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
