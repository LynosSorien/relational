package com.djorquab.relational.relational.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Utils {

	public static final Map<String, Object> createParams(Object ... objects) {
		Map<String, Object> params = null;
		if (objects != null) {
			log.debug("Current params {} : Size {}", objects, objects.length);
			params = new HashMap<>();
			for (int i = 0; i < objects.length - 1; i = i + 1) {
				params.put(objects[i] != null ? objects[i].toString() : "null", objects[i + 1]);
			}
		}
		return params;
	}

	public static final Date dateFromString(String textDate, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(textDate);
	}
}
