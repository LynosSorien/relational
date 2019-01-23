package com.djorquab.relational.relational.rest;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
	
	public MvcResult simpleGetPetition(String path) {
		MvcResult result;
		try {
			result = mvc.perform(MockMvcRequestBuilders.get(path)
					).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		return result;
	}
}
