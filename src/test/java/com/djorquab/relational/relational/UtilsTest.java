package com.djorquab.relational.relational;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.bo.RelationNameBO;
import com.djorquab.relational.relational.commons.UserDTO;
import com.djorquab.relational.relational.utils.BackofficeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
	
	@Test
	@WithMockUser(username="testuser")
	public void paramsWithUser() {
		Map<String, Object> params = BackofficeUtils.initialAuthParams();
		Assert.assertNotNull(params);
		Assert.assertTrue(params.containsKey("user"));
		UserDTO user = (UserDTO)params.get("user");
		Assert.assertEquals(user.getUsername(), "testuser");
	}
	
	@Test
	@WithMockUser(username = "testuser")
	public void modelAndViewWithTableDefinition() {
		ModelAndView sample = BackofficeUtils.createModelAndViewWithTableDefinition("sample", RelationNameBO.class);
		Assert.assertNotNull(sample);
		Assert.assertEquals(sample.getViewName(), "sample");
	}
}
