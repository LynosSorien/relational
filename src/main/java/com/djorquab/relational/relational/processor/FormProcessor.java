package com.djorquab.relational.relational.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;

import com.djorquab.relational.relational.commons.FormDefinition;
import com.djorquab.relational.relational.commons.FormFieldDefinition;

public class FormProcessor extends Processor<FormDefinition, FormField> {

	@Override
	protected FormDefinition processField(FormField annotation,
			FormDefinition instance, Field field) {
		if (instance.getFields() == null) {
			instance.setFields(new LinkedList<>());
		}
		instance.getFields().add(
				FormFieldDefinition.builder()
					.variable(field.getName())
					.colspan(annotation.colspan())
					.position(annotation.position())
					.type(annotation.type())
					.label(annotation.label())
					.build()
		);
		return instance;
	}
	
	@Override
	protected <CA extends Annotation> FormDefinition feedClassAnnotation(
			FormDefinition instance, CA annotation) {
		FormDef def = (FormDef) annotation;
		instance.setMethod(def.method());
		instance.setSubmitButtonLabel(def.submitButtonLabel());
		instance.setSubmitLink(def.submitLink());
		instance.setTitle(def.title());
		return instance;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <CA extends Annotation> Class<CA> classAnnotation() {
		return (Class<CA>) FormDef.class;
	}
	
	@Override
	protected FormDefinition postProcess(FormDefinition instance) {
		if (instance != null && instance.getFields() != null && instance.getFields().size() > 1) {
			Collections.sort(instance.getFields());
		}
		return instance;
	}

	@Override
	protected Class<FormField> annotationClass() {
		return FormField.class;
	}

	@Override
	protected Class<FormDefinition> instanceClass() {
		return FormDefinition.class;
	}

}
