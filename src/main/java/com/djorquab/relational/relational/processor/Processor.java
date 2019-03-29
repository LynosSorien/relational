package com.djorquab.relational.relational.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Processor<E, A extends Annotation> {
	@Getter
	private Map<String, E> scannedItems;
	
	public Processor() {
		this.scannedItems = new HashMap<>();
	}
	
	public <T, CA extends Annotation> E process(Class<T> clazz) {
		if (clazz != null) {
			if (scannedItems.containsKey(clazz.getName())) {
				return scannedItems.get(clazz.getName());
			}
			Class<CA> classAnnotation = classAnnotation();
			E instance;
			if (classAnnotation != null) {
				CA ca = clazz.getAnnotation(classAnnotation);
				instance = getObjectInstance(ca);
				if (ca != null) {
					instance = feedClassAnnotation(instance, ca);
				}
			} else {
				instance = getObjectInstance(null);
			}
			Class<A> annotationClass = annotationClass();
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null) {
				for (int i = 0 ; i < fields.length; i++) {
					Field field = fields[i];
					A annotation = field.getAnnotation(annotationClass);
					if (annotation != null && passConditions(annotation, instance, field)) {
						instance = processField(annotation, instance, field);
					}
				}
			}
			put(clazz.getName(), instance);
			return postProcess(instance);
		}
		return null;
	}
	
	protected void put(String clazz, E e) {
		this.scannedItems.put(clazz,e);
	}
	
	protected <CA extends Annotation> E feedClassAnnotation(E instance, CA annotation) {
		return instance;
	}
	protected <CA extends Annotation> Class<CA> classAnnotation() {
		return null;
	}
	
	protected abstract E postProcess(E instance);
	
	protected boolean passConditions(A annotation, E instance, Field field) {
		return true;
	}
	protected abstract E processField(A annotation, E instance, Field field);
	protected <CA extends Annotation>E getObjectInstance(CA classAnnotation) {
		return getInstance(instanceClass());
	}

	protected <O> O getInstance(Class<O> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException|IllegalAccessException e) {
			log.error("Unexpected error occured while trying to create new instance {}", e);
		}
		return null;
	}

	protected abstract Class<A> annotationClass();
	protected abstract Class<E> instanceClass();
}
