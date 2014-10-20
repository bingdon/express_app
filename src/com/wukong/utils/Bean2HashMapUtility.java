package com.wukong.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wukong.support.debug.AppLog;

public class Bean2HashMapUtility {
	public static HashMap<String, Object> objToHash(Object obj)
			throws IllegalArgumentException, IllegalAccessException {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Class<?> clazz = obj.getClass();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();

		do {
			clazzs.add(clazz);
			clazz = clazz.getSuperclass();
		} while (!clazz.equals(Object.class));

		for (Class<?> iClazz : clazzs) {
			Field[] fields = iClazz.getDeclaredFields();
			for (Field field : fields) {
				Object objVal = null;
				field.setAccessible(true);
				objVal = field.get(obj);
				hashMap.put(field.getName(), objVal);
			}
		}
		AppLog.i("生成:" + hashMap);
		return hashMap;
	}

	public static HashMap<String, String> strToHash(Object obj)
			throws IllegalArgumentException, IllegalAccessException {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		Class<?> clazz = obj.getClass();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();

		do {
			clazzs.add(clazz);
			clazz = clazz.getSuperclass();
		} while (!clazz.equals(Object.class));

		for (Class<?> iClazz : clazzs) {
			Field[] fields = iClazz.getDeclaredFields();
			for (Field field : fields) {
				Object objVal = null;
				field.setAccessible(true);
				objVal = field.get(obj);
				String str = "" + objVal;
				hashMap.put(field.getName(), str);
			}
		}
		AppLog.i("生成:" + hashMap);
		return hashMap;
	}

}
