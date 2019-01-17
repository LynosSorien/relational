package com.djorquab.relational.relational.processor;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;

import com.djorquab.relational.relational.commons.ColumnDefinition;
import com.djorquab.relational.relational.commons.TableDefinition;

public class TableDefinitionProcessor extends Processor<TableDefinition, ViewColumn> {
	
	@Override
	public TableDefinition processField(ViewColumn annotation, TableDefinition instance, Field field) {
		ColumnDefinition definition = null;
		definition = ColumnDefinition.builder()
				.header(annotation.header())
				.variable(field.getName())
				.link(annotation.link())
				.linkType(annotation.linkType())
				.pathVariable(annotation.pathVariable())
				.withLink(annotation.linkable())
				.position(annotation.position())
				.type(annotation.type())
				.build();
		if (instance.getColumns() == null) {
			instance.setColumns(new LinkedList<>());
		}
		instance.getColumns().add(definition);
		return instance;
	}
	
	@Override
	protected TableDefinition postProcess(TableDefinition instance) {
		Collections.sort(instance.getColumns());
		return instance;
	}
	
	@Override
	public Class<ViewColumn> annotationClass() {
		return ViewColumn.class;
	}
	
	@Override
	protected Class<TableDefinition> instanceClass() {
		return TableDefinition.class;
	}
}
