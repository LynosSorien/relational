package com.djorquab.relational.relational.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.djorquab.relational.relational.commons.ActionType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionDefinition {
	boolean active() default false;
	ActionType type() default ActionType.DELETE;
	boolean pathVariable() default false;
	String path() default "";
	String requestParam() default "";
	String method() default "";
}
