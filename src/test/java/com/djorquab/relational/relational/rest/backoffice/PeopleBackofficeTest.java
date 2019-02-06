package com.djorquab.relational.relational.rest.backoffice;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.repositories.PeopleRepository;
import com.djorquab.relational.relational.services.PeopleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.rest.AbstractRestTest;
import com.djorquab.relational.relational.rest.TestParam;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeopleBackofficeTest extends AbstractRestTest {
	@Autowired
	private PeopleService service;

	@Autowired
	private PeopleRepository repository;

	@Before
	public void clean() {
		repository.deleteAll();
	}

	@Test
	@WithMockUser(username = "testing")
	public void simplePetitions() {
		get("/backoffice/people");
		get("/backoffice/people/paging");
		get("/backoffice/people/creation");
		get("/backoffice/people/edit", new TestParam<>("personId", 1L));
	}

	@Test
	@WithMockUser(username = "testing")
	public void createUser() {
		post("/api/people", PersonBO.builder().name("Claire").surname("Farron").birthday(Calendar.getInstance().getTime()).deathdate(Calendar.getInstance().getTime()).description("LORE IPSUM").build());
		Assert.assertEquals(repository.count(), 1L);
		List<PersonBO> persons = service.findAll();
		PersonBO claire = persons.get(0);
		Assert.assertEquals(claire.getName(), "Claire");
	}

	@Test
	@WithMockUser(username = "testing")
	public void createUserUsingBackoffice() {
		postWithModelAttribute("/backoffice/people", null,
				PersonBO.builder().name("Claire").surname("Farron").birthday(Calendar.getInstance().getTime()).deathdate(Calendar.getInstance().getTime()).description("LORE IPSUM").build());
		Assert.assertEquals(repository.count(), 1L);
		List<PersonBO> persons = service.findAll();
		PersonBO claire = persons.get(0);
		Assert.assertEquals(claire.getName(), "Claire");
	}

	@Test
	@WithMockUser(username = "testing")
	public void createAndDeleteUser() {
		post("/api/people", PersonBO.builder().name("Claire").surname("Farron").birthday(Calendar.getInstance().getTime()).deathdate(Calendar.getInstance().getTime()).description("LORE IPSUM").build());
		Assert.assertEquals(repository.count(), 1L);
		List<PersonBO> persons = service.findAll();
		PersonBO claire = persons.get(0);
		Assert.assertEquals(claire.getName(), "Claire");
		delete("/backoffice/people", new TestParam<>("personId", claire.getId()));
		Assert.assertEquals(repository.count(), 0L);
	}
	
}
