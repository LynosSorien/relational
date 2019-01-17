package com.djorquab.relational.relational.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Utils {
	
	@SafeVarargs
	public static final <T>Map<String, Object> createParams(Object ... objects) {
		log.info("Current params {} : Size {}", objects, objects != null ? objects.length : "NULL");
		if (objects == null || objects.length%2 != 0) {
			return null;
		}
		Map<String, Object> params = new HashMap<>();
		for (int i = 0 ; i < objects.length-1; i = i+1) {
			params.put(objects[i] != null ? objects[i].toString() : null, objects[i+1]);
		}
		return params;
	}
}
