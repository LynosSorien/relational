package com.djorquab.relational.relational.processors;

import com.djorquab.relational.relational.AbstractTest;
import com.djorquab.relational.relational.commons.FieldType;
import com.djorquab.relational.relational.commons.Filter;
import com.djorquab.relational.relational.commons.FormDefinition;
import com.djorquab.relational.relational.commons.FormFieldDefinition;
import com.djorquab.relational.relational.components.filters.PeopleSearcher;
import com.djorquab.relational.relational.processor.*;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterProcessorTest extends AbstractTest {
	private FilterProcessor processor = Processors.filterProcessorInstance();
	
	public static final String ID = "ID";
	public static final String NAME = "Name";
	public static final String SUBMIT_BUTTON = "Test";
	public static final String SUBMIT_LINK = "www.google.com";
	
	@Test
	public void nullTest() {
		Assert.assertNull(processor.process(null));
	}
	
	@Test
	public void oneFilterTestWithDefaultValues() {
		List<Filter> filters = processor.process(SimpleFilterObject.class);
		Assert.assertNotNull(filters);
		Assert.assertEquals(1L, filters.size());
		Filter filter = filters.get(0);
		Assert.assertEquals(NAME, filter.getLabel());
		Assert.assertEquals("name", filter.getVariable());
		Assert.assertEquals("text", filter.getType());
		Assert.assertEquals(0L, filter.getOptions().size());
	}

	@Test
	public void peopleFilterTest() {
		List<Filter> filters = processor.process(PeopleSearcher.class);
		Assert.assertNotNull(filters);
		Assert.assertNotEquals(0L, filters.size());
		filters.stream().forEach(Assert::assertNotNull);
	}
}

@Data
class SimpleFilterObject {
	@FieldFilter(label = FormProcessorTest.NAME)
	private String name;
}

@Data
class SimpleFilterObjectTwoFields {
	@FormField(label = FormProcessorTest.ID, type = FieldType.TEXT)
	private String id;
	@FormField(label = FormProcessorTest.NAME, type = FieldType.TEXT)
	private String name;
}