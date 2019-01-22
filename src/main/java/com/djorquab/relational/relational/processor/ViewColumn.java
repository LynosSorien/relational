package com.djorquab.relational.relational.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.djorquab.relational.relational.commons.ColumnType;
import com.djorquab.relational.relational.commons.LinkType;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ViewColumn {
	String header();
	boolean linkable() default false;
	LinkType linkType() default LinkType.LINK;
	String link() default "";
	boolean pathVariable() default false;
	int position() default -1;
	ColumnType type() default ColumnType.TEXT;
	
	ActionDefinition action() default @ActionDefinition(active = false);
}
