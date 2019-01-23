package com.djorquab.relational.relational.rest.backoffice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.rest.AbstractRestTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestPagesController extends AbstractRestTest {
	
	@Test
	public void publicPages() {
		simpleGetPetition("/");
	}
	
	@Test
	public void simpleGreetingsController() {
		simpleGetPetition("/greetings");
	}
}
