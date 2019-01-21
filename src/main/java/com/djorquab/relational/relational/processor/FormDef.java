package com.djorquab.relational.relational.processor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FormDef {
	String title() default "";
	String submitLink();
	String method() default "POST";
	String submitButtonLabel() default "Submit";// TODO change for identificators to bundle.
}
