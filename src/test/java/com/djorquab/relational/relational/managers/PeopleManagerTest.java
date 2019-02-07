package com.djorquab.relational.relational.managers;

import com.djorquab.relational.relational.AbstractTest;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.services.PeopleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeopleManagerTest extends AbstractTest {
	public static final String NAME = "Isaac";
	public static final String SURNAME = "Newton";
	
	@Autowired
	private PeopleManager manager;

	@Autowired
	private PeopleService service;
	
	private PersonBO create(String name, String surname) {
		return PersonBO.builder()
				.name(name)
				.surname(surname)
				.build();
	}

	@Before
	public void deleteAll() {
		manager.deleteAll();
	}
	
	@Test
	public void createAndDelete() {
		PersonBO person = manager.create(create("Tony", "Stark"));
		Assert.assertNotNull(person.getId());
		manager.delete(person);
		person = service.getById(person.getId());
		Assert.assertNull(person);
	}
}
