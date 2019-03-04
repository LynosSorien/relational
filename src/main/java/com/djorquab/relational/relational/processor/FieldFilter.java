package com.djorquab.relational.relational.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldFilter {
    String label();
    String variable() default "";
    String type() default "text";
    String[] options() default {};
    boolean shown() default false;
    boolean popup() default true;
}
