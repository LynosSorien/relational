package com.djorquab.relational.relational.rest.backoffice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.rest.AbstractRestTest;
import com.djorquab.relational.relational.rest.TestParam;
import com.djorquab.relational.relational.services.RelationNameService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestRelationNamesBackofficeTest extends AbstractRestTest {
	@Autowired
	private RelationNameService service;
	
	@Test
	@WithMockUser(username = "testing")
	public void simplePetitions() {
		get("/backoffice/relational/names");
		get("/backoffice/relational/names/paging");
	}
	
	@Test
	@WithMockUser(username = "testing")
	public void createAndRemoveRelation() {
		RelationNameBO saved = service.save(RelationNameBO.builder().name("TEST").build());
		Assert.assertNotNull(saved);
		Assert.assertEquals(saved.getName(), "TEST");
		
		delete("/backoffice/relational/names", new TestParam<Long>("relationId",saved.getId()));
		
		Assert.assertNull(service.getById(saved.getId()));
	}
	
}
