package com.djorquab.relational.relational.processor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import com.djorquab.relational.relational.commons.Action;
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
		
		ActionDefinition[] actions = annotation.actions();
		for (ActionDefinition action : actions) {
			fillAction(action, instance, field);
		}
		return instance;
	}

	private void fillAction(ActionDefinition actionDefinition, TableDefinition instance, Field field) {
		if (actionDefinition != null && actionDefinition.active()) {
			String method = actionDefinition.method();
			if ("".equals(method)) {
				switch(actionDefinition.type()) {
					case EDIT:
						method = "GET";
					case DELETE:
						method = "DELETE";
					default:
						method = "POST";
					break;
				}
			}
			Action action = Action.builder()
					.path(actionDefinition.path())
					.type(actionDefinition.type())
					.pathVariable(actionDefinition.pathVariable())
					.requestParam("".equals(actionDefinition.requestParam()) ? field.getName() : actionDefinition.requestParam())
					.variable(field.getName())
					.method(method)
					.build();
			if (instance.getActions() == null) {
				instance.setActions(new LinkedList<>());
			}
			instance.getActions().add(action);
		}
	}
	
	@Override
	protected TableDefinition postProcess(TableDefinition instance) {
		if (instance.getColumns() != null && instance.getColumns().size() > 1) {
			Collections.sort(instance.getColumns());
		}
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
