package com.djorquab.relational.relational.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.djorquab.relational.relational.AbstractTest;
import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.repositories.RelationNamesRepository;
import com.djorquab.relational.relational.services.RelationNameService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RelationNamesTest extends AbstractTest {
	@Autowired
	private RelationNameService service;
	
	@Autowired
	private RelationNamesRepository repository;
	
	private RelationNameBO create(String name) {
		return RelationNameBO.builder().name(name).build();
	}
	
	@Before
	public void cleanBootstrapping() {
		repository.deleteAll();
	}
	
	@Test
	public void noValues() {
		Assert.assertEquals(repository.count(), 0L);
	}
	
	@Test
	public void insertOneElement() {
		RelationNameBO relation = service.save(create("testing"));
		Assert.assertEquals(repository.count(), 1L);
		Assert.assertNotNull(relation.getId());
	}
	
	@Test
	public void loadById() {
		RelationNameBO saved = service.save(create("testing"));
		RelationNameBO relation = service.getById(saved.getId());
		Assert.assertNotNull(relation);
		Assert.assertEquals("testing", relation.getName());
		Assert.assertEquals(relation.getId(), saved.getId());
	}
	
	@Test
	public void deleteById() {
		RelationNameBO saved = service.save(create("testing"));
		service.delete(saved.getId());
		Assert.assertEquals(0L, repository.count());
	}
}
