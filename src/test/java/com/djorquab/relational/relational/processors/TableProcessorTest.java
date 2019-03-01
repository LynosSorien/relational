package com.djorquab.relational.relational.processors;

import com.djorquab.relational.relational.AbstractTest;
import lombok.Data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djorquab.relational.relational.commons.Action;
import com.djorquab.relational.relational.commons.ActionType;
import com.djorquab.relational.relational.commons.TableDefinition;
import com.djorquab.relational.relational.processor.ActionDefinition;
import com.djorquab.relational.relational.processor.Processors;
import com.djorquab.relational.relational.processor.TableDefinitionProcessor;
import com.djorquab.relational.relational.processor.ViewColumn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableProcessorTest extends AbstractTest {
	private TableDefinitionProcessor processor = Processors.tableDefinitionInstance();
	
	public static final String NAME_HEADER = "Name";
	public static final String NAME_REQUEST = "nameRequest";
	public static final String TEST_PATH = "/path/test/one";
	
	@Test
	public void testSimpleColumnProcessor() {
		TableDefinition definition = processor.process(TableTestOneColumnItem.class);
		Assert.assertNotNull(definition);
		Assert.assertEquals(1L, definition.getColumns().size());
		Assert.assertEquals(NAME_HEADER, definition.getColumns().get(0).getHeader());
	}
	
	@Test
	public void testSimpleActionProcessor() {
		TableDefinition definition = processor.process(TableTestOneActionItem.class);
		Assert.assertNotNull(definition);
		Assert.assertEquals(1L, definition.getActions().size());
		Action action = definition.getActions().get(0);
		Assert.assertEquals(TEST_PATH, action.getPath());
		Assert.assertEquals(NAME_REQUEST, action.getRequestParam());
		Assert.assertNotNull(action.getVariable());
		Assert.assertNotEquals(action.getRequestParam(), action.getVariable());
	}
	
	@Test
	public void testNoAction() {
		TableDefinition definition = processor.process(TableTestOneColumnItem.class);
		Assert.assertNotNull(definition);
		Assert.assertNull(definition.getActions());
	}
	
	@Test
	public void cachingProcessor() {
		TableDefinition definition = processor.process(TableTestOneColumnItem.class);
		Assert.assertTrue(definition == processor.process(TableTestOneColumnItem.class));
	}
}


@Data
class TableTestOneColumnItem {
	@ViewColumn(header = TableProcessorTest.NAME_HEADER)
	private String name;
}

@Data
class TableTestOneActionItem {
	@ViewColumn(header = TableProcessorTest.NAME_HEADER,
			actions = @ActionDefinition(active = true, path = TableProcessorTest.TEST_PATH, type = ActionType.DELETE, pathVariable = false, requestParam = TableProcessorTest.NAME_REQUEST))
	private String name;
}