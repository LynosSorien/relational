package com.djorquab.relational.relational.processor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.djorquab.relational.relational.commons.FieldType;

@Documented
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormField {
	String label();
	FieldType type() default FieldType.TEXT;

	Searcher searcher() default @Searcher(searcher = Void.class, tableDefinition = Void.class);

	int position() default -1;
	int colspan() default 12;
	boolean hidden() default false;

	@Retention(RetentionPolicy.RUNTIME)
	@interface Searcher {
		Class<?> searcher();
		String paginationLink() default "";
		String searchLink() default "";
		String acceptLink() default "";
		Class<?> tableDefinition();
	}
}
