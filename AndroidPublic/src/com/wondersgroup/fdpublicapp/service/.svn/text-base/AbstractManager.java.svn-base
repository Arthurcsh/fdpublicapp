package com.wondersgroup.fdpublicapp.service;

import java.util.HashMap;
import java.util.Map;

public class AbstractManager {

	private static Map<String,Object> serverMap = new HashMap<String,Object>();
	
	public static Object get(String key) {
		return get(key, null);
	}

	public static String getString(String key) {
		Object value = get(key, String.class);
		return (String) value;
	}

	public static int getInt(String key) {
		Object value = get(key, Integer.class);
		return (value instanceof Integer) ? ((Integer) value).intValue() : 0;
	}

	public static long getLong(String key) {
		Object value = get(key, Long.class);
		return (value instanceof Long) ? ((Long) value).longValue() : 0;
	}

	public static float getFloat(String key) {
		Object value = get(key, Float.class);
		return (value instanceof Float) ? ((Float) value).floatValue() : 0;
	}

	public static void putStyleValue(String key, int value) {
		putStyleValue(key, new Integer(value));
	}

	public static void putStyleValue(String key, long value) {
		long oldValue = getLong(key);
		if (oldValue == value)
			return;
		putValue(key, new Long(value));
	}

	public static void putStringValue(String key, Object value) {
		Object oldValue = get(key);
		if (oldValue == value)
			return;
		putValue(key, value);
	}

	public static void putValue(String key, Object value) {
		serverMap.put(key, value);
	}

	public static Object get(String key, Class<?> clazz) {
		Object value = serverMap.get(key);
		if (value != null && clazz != null) {
			if (!clazz.isAssignableFrom(value.getClass())) {
				throw new IllegalStateException("\nValue with key '" + key
						+ "' in ServiceManager should be instance of " + clazz
						+ "\nactually the value is: " + value
						+ ", an instance of " + value.getClass());
			}
		}
		return value;
	}
	
	public static Map<String,Object> getSuperManager() {
		return serverMap;
	}
}
