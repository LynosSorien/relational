package com.djorquab.relational.relational.rest;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.NamedDTO;
import com.djorquab.relational.relational.services.RelationNameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class RestRelationNamesTest {
	public static final String SIMPLE_RELATION_TEST = "Test";
	public static final String SIMPLE_RELATION_TEST_2 = "Test2";

	@Autowired
	private RelationNameService service;
	
	//@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	private String toJson(Object o) {
		try {
			return jsonMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unused")
	private <T> T fromJson(String json, Class<T> clazz) {
		try {
			return jsonMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private <T> T fromJson(String json, TypeReference<T> typeReference) {
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
	
	@Test
	public void serializeTest() throws Exception {
		String json = jsonMapper.writeValueAsString(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build());
		Assert.assertNotNull(json);
		Assert.assertFalse(json.trim().isEmpty());
	}
	
	@Test
	public void createSimpleRelation() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	}
	
	@Test
	public void createAndReadSimpleRelation() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		
		result = mvc.perform(MockMvcRequestBuilders.get("/api/relation/names/list")
				).andReturn();
		
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		
		List<RelationNameBO> relations = fromJson(result.getResponse().getContentAsString(), new TypeReference<List<RelationNameBO>>(){});
		
		Assert.assertNotNull(relations);
		Assert.assertEquals(relations.size(), 1L);
		
		log.info("MVC Object {}", relations.get(0));
		
		
		Assert.assertEquals(relations.get(0).getName(), SIMPLE_RELATION_TEST);
	}
	
	
	@Test
	public void createAndReadTwoRelations() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	
		result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST_2).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		
		result = mvc.perform(MockMvcRequestBuilders.get("/api/relation/names/list")
				).andReturn();
		
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		
		List<RelationNameBO> relations = fromJson(result.getResponse().getContentAsString(), new TypeReference<List<RelationNameBO>>(){});
		
		Assert.assertNotNull(relations);
		Assert.assertEquals(relations.size(), 2L);
	}
	
	@Test
	public void failToCreateTwoSameRelations() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	
		result = mvc.perform(MockMvcRequestBuilders.post("/api/relation/names")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(NamedDTO.builder().name(SIMPLE_RELATION_TEST).build()))).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		result = mvc.perform(MockMvcRequestBuilders.get("/api/relation/names/list")
				).andReturn();
		
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		
		List<RelationNameBO> relations = fromJson(result.getResponse().getContentAsString(), new TypeReference<List<RelationNameBO>>(){});
		
		Assert.assertNotNull(relations);
		Assert.assertEquals(relations.size(), 1L);
	}
}
