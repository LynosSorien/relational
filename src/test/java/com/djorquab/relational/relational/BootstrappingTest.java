package com.djorquab.relational.relational;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.jobs.Bootstrapping;
import com.djorquab.relational.relational.services.RelationNameService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class BootstrappingTest {
	@Autowired
	private Bootstrapping bootstrapping;
	
	@Autowired
	private RelationNameService relationNameService;
	
	@Test
	public void testBootstrapping() {
		List<RelationNameBO> relations = relationNameService.findAll();
		Assert.assertNotNull(relations);
		Assert.assertNotEquals(relations.size(), 0L);
	}
}
