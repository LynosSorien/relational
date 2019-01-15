package com.djorquab.relational.relational.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
	
	public static final Map<String, Object> createParams(Object ... objects) {
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
