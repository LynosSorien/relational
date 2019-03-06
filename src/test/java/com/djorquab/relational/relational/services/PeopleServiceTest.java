package com.djorquab.relational.relational.services;

import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.managers.PeopleManager;
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

	@Autowired
	private PeopleManager manager;
	
	private PersonBO create(String name, String surname) {
		return PersonBO.builder()
				.name(name)
				.surname(surname)
				.build();
	}

	private PersonBO save(PersonBO person) {
		return service.save(person);
	}
	
	@Test
	public void initialTest() {
		PersonBO current = service.save(create(NAME, SURNAME));
		PersonBO readed = service.getById(current.getId());
		Assert.assertNotNull(readed);
		Assert.assertEquals(readed.getId(), current.getId());
		Assert.assertEquals(readed.getName(), NAME);
	}

	@Test
	public void findPagedTest() {
		manager.deleteAll();

		Assert.assertEquals(0L, service.count());

		save(create("Serah", "Farron"));
		save(create("Ecne", "DS"));
		save(create("Freya", "Asgard"));
		save(create("Dante", ""));
		save(create(NAME, SURNAME));

		Assert.assertEquals(5L, service.count());

		PagedResult<PersonBO> pagedResult = service.findAllPaged(0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(5L, pagedResult.getElements().size());
		Assert.assertEquals(5L, pagedResult.getTotalElements());

		pagedResult = service.findAllPaged(0, 2);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(2L, pagedResult.getElements().size());
		Assert.assertEquals(5L, pagedResult.getTotalElements());

		pagedResult = service.findPaged("Ecn", 0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(1L, pagedResult.getElements().size());
		Assert.assertEquals(1L, pagedResult.getTotalElements());

		pagedResult = service.findPagedSurname("r", 0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(2L, pagedResult.getElements().size());
		Assert.assertEquals(2L, pagedResult.getTotalElements());

		pagedResult = service.findPaged("r", "n", 0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(1L, pagedResult.getElements().size());
		Assert.assertEquals(1L, pagedResult.getTotalElements());

		pagedResult = service.findPaged("Ecn", null, 0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(1L, pagedResult.getElements().size());
		Assert.assertEquals(1L, pagedResult.getTotalElements());

		pagedResult = service.findPaged(null, "r", 0, 10);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(2L, pagedResult.getElements().size());
		Assert.assertEquals(2L, pagedResult.getTotalElements());

		pagedResult = service.findPaged(null, null, 0, 2);
		Assert.assertNotNull(pagedResult);
		Assert.assertEquals(2L, pagedResult.getElements().size());
		Assert.assertEquals(5L, pagedResult.getTotalElements());
	}
}
