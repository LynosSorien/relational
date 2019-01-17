package com.djorquab.relational.relational.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public abstract class Processor<E, A extends Annotation> {
	@Getter
	private Map<String, E> scannedItems;
	
	public Processor() {
		this.scannedItems = new HashMap<>();
	}
	
	public <T>E process(Class<T> clazz) {
		if (clazz != null) {
			if (scannedItems.containsKey(clazz.getName())) {
				return scannedItems.get(clazz.getName());
			}
			E instance = getObjectInstance();
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
	
	
	protected E postProcess(E instance) {
		return instance;
	}
	protected boolean passConditions(A annotation, E instance, Field field) {
		return true;
	}
	protected abstract E processField(A annotation, E instance, Field field);
	protected E getObjectInstance() {
		try {
			return instanceClass().newInstance();
		} catch (InstantiationException|IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected abstract Class<A> annotationClass();
	protected abstract Class<E> instanceClass();
}
