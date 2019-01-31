package com.djorquab.relational.relational;

import java.text.ParseException;
import java.util.Date;
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
import com.djorquab.relational.relational.utils.Utils;

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
	
	@Test
	public void utilsTest() {
		Assert.assertEquals(0L, Utils.createParams().size());
		Map<String, Object> params = Utils.createParams("test-param", 50L);
		Assert.assertNotNull(params);
		Assert.assertEquals(params.size(), 1L);
		Assert.assertTrue(params.containsKey("test-param"));
		Assert.assertEquals(params.get("test-param"), 50L);
	}

	@Test
	public void createDate() throws ParseException {
		Date date = Utils.dateFromString("2019-01-31", "yyyy-MM-dd");
		Assert.assertNotNull(date);
	}
}
