package com.djorquab.relational.relational.rest;

import com.djorquab.relational.relational.BackofficeConstants;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.djorquab.relational.relational.AbstractTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestTest extends AbstractTest {
	@Autowired
	protected ObjectMapper jsonMapper;
	
	protected MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	public String toJson(Object o) {
		try {
			return jsonMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> T fromJson(String json, Class<T> clazz) {
		try {
			return jsonMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public <T> T fromJson(String json, TypeReference<T> typeReference) {
		try {
			return jsonMapper.readValue(json, typeReference);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	public MvcResult get(String path, TestParam<?> ... params) {
		MvcResult result;
		try {
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(path);
			if (params != null) {
				for (TestParam<?> param : params) {
					builder = builder.param(param.getParam(), param.getValue() != null ? param.getValue().toString() : null);
				}
			}
			result = mvc.perform(builder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		return result;
	}

	public MvcResult post(HttpStatus expected, String path, Object requestBody, TestParam<?> ... params) {
		return postWithModelAttribute(expected, path, requestBody, null, params);
	}

	public MvcResult post(String path, Object requestBody, TestParam<?> ... params) {
		return postWithModelAttribute(path, requestBody, null, params);
	}

	public MvcResult postWithModelAttribute(String path, Object requestBody, Object modelAttribute, TestParam<?> ... params) {
		return postWithModelAttribute(HttpStatus.OK, path, requestBody, modelAttribute, params);
	}

	public MvcResult postWithModelAttribute(HttpStatus expected, String path, Object requestBody, Object modelAttribute, TestParam<?> ... params) {
		MvcResult result;
		try {
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(path);
			if (requestBody != null) {
				builder = builder.contentType(MediaType.APPLICATION_JSON).content(toJson(requestBody));
			}
			if (modelAttribute != null) {
				builder.flashAttr(BackofficeConstants.FORM_OBJECT, modelAttribute);
			}
			if (params != null) {
				for (TestParam<?> param : params) {
					builder = builder.param(param.getParam(), param.getValue() != null ? param.getValue().toString() : null);
				}
			}
			result = mvc.perform(builder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Assert.assertEquals(expected.value(), result.getResponse().getStatus());
		return result;
	}
	
	public MvcResult delete(String path, TestParam<?> ... params) {
		MvcResult result;
		try {
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(path);
			if (params != null) {
				for (TestParam<?> param : params) {
					builder = builder.param(param.getParam(), param.getValue() != null ? param.getValue().toString() : null);
				}
			}
			result = mvc.perform(builder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		return result;
	}
}
