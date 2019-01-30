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
import com.djorquab.relational.relational.bo.PersonBO;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeopleServiceTest extends AbstractTest {
	public static final String NAME = "Isaac";
	public static final String SURNAME = "Newton";
	
	@Autowired
	private PeopleService service;
	
	private PersonBO create(String name, String surname) {
		return PersonBO.builder()
				.name(name)
				.surname(surname)
				.build();
	}
	
	@Test
	public void initialTest() {
		PersonBO current = service.save(create(NAME, SURNAME));
		PersonBO readed = service.getById(current.getId());
		Assert.assertNotNull(readed);
		Assert.assertEquals(readed.getId(), current.getId());
		Assert.assertEquals(readed.getName(), NAME);
	}
}
