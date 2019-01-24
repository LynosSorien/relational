package com.djorquab.relational.relational.rest.backoffice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.rest.AbstractRestTest;
import com.djorquab.relational.relational.rest.TestParam;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeopleBackofficeTest extends AbstractRestTest {
	
	@Test
	@WithMockUser(username = "testing")
	public void simplePetitions() {
		get("/backoffice/people");
		get("/backoffice/people/paging");
		get("/backoffice/people/creation");
		get("/backoffice/people/edit", new TestParam<Long>("personId", 1L));
	}
	
}
