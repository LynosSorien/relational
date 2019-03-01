package com.djorquab.relational.relational.processors;

import com.djorquab.relational.relational.AbstractTest;
import lombok.Data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djorquab.relational.relational.commons.FieldType;
import com.djorquab.relational.relational.commons.FormDefinition;
import com.djorquab.relational.relational.commons.FormFieldDefinition;
import com.djorquab.relational.relational.processor.FormDef;
import com.djorquab.relational.relational.processor.FormField;
import com.djorquab.relational.relational.processor.FormProcessor;
import com.djorquab.relational.relational.processor.Processors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormProcessorTest extends AbstractTest {
	private FormProcessor processor = Processors.formProcessorInstance();
	
	public static final String ID = "ID";
	public static final String NAME = "Name";
	public static final String SUBMIT_BUTTON = "Test";
	public static final String SUBMIT_LINK = "www.google.com";
	
	@Test
	public void nullTest() {
		Assert.assertNull(processor.process(null));
	}
	
	@Test
	public void oneFieldTestWithoutHeader() {
		FormDefinition definition = processor.process(SimpleFormObject.class);
		Assert.assertNotNull(definition);
		Assert.assertNull(definition.getMethod());
		Assert.assertEquals(1L, definition.getFields().size());
		FormFieldDefinition field = definition.getFields().get(0);
		Assert.assertEquals(NAME, field.getLabel());
		Assert.assertEquals("name", field.getVariable());
		Assert.assertEquals(FieldType.TEXT, field.getType());
	}
	
	@Test
	public void oneFieldTestWithHeader() {
		FormDefinition definition = processor.process(SimpleFormObjectWithHeader.class);
		Assert.assertNotNull(definition);
		Assert.assertEquals(definition.getMethod(), "GET");
		Assert.assertEquals(definition.getSubmitButtonLabel(), SUBMIT_BUTTON);
		Assert.assertEquals(definition.getSubmitLink(), SUBMIT_LINK);
		Assert.assertEquals(1L, definition.getFields().size());
		FormFieldDefinition field = definition.getFields().get(0);
		Assert.assertEquals(NAME, field.getLabel());
		Assert.assertEquals("name", field.getVariable());
		Assert.assertEquals(FieldType.TEXT, field.getType());
	}
	
	@Test
	public void twoFieldsTestWithoutHeader() {
		FormDefinition definition = processor.process(SimpleFormObjectTwoFields.class);
		Assert.assertNotNull(definition);
		Assert.assertNull(definition.getMethod());
		Assert.assertEquals(2L, definition.getFields().size());
	}
}

@Data
class SimpleFormObject {
	@FormField(label = FormProcessorTest.NAME, type = FieldType.TEXT)
	private String name;
}

@Data
class SimpleFormObjectTwoFields {
	@FormField(label = FormProcessorTest.ID, type = FieldType.TEXT)
	private String id;
	@FormField(label = FormProcessorTest.NAME, type = FieldType.TEXT)
	private String name;
}

@Data
@FormDef(method = "GET", submitButtonLabel = FormProcessorTest.SUBMIT_BUTTON, submitLink = FormProcessorTest.SUBMIT_LINK)
class SimpleFormObjectWithHeader {
	@FormField(label = FormProcessorTest.NAME, type = FieldType.TEXT)
	private String name;
}