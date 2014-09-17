package com.wondersgroup.fdpublicapp.common.util;

import org.json.JSONObject;

import android.util.Log;

public class JsonAnalyzeKeyAndValue {
	
	private static final String TAG = "JsonAnalyzeKeyAndValue";

	/**
	 * 将json键值对分别解析到数组中
	 * 
	 * @param jsonject
	 *            需要解析的json对象
	 * @param type
	 *            决定返回值的内容：键或值
	 * @return type="key"：返回json对象中"键"的字符串， type="key""value":返回json对象中"值"的字符串
	 */
	public static String[] analyzeJsonToArray(JSONObject jsonject, String type) {

		String string = jsonject.toString();
		string = string.replace("}", "");
		string = string.replace("{", "");
		string = string.replace("\"", "");
		String[] strings = string.split(",");

		if (type.equals("key")) {
			String[] stringsNum = new String[strings.length];
			for (int i = 0; i < strings.length; i++) {
				stringsNum[i] = strings[i].split(":")[0];
			}
			
			return stringsNum;
		} else if (type.equals("value")) {
			String[] stringsName = new String[strings.length];
			for (int i = 0; i < strings.length; i++) {
				stringsName[i] = strings[i].split(":")[1];
			}
			
			return stringsName;
		} else {
			return null;
		}
	}
	
	public static String[] analyzeJsonToArray(String jsonject, String type) {

		String string = jsonject;
		string = string.replace("}", "");
		string = string.replace("{", "");
		string = string.replace("\"", "");
		String[] strings = string.split(",");

		if (type.equals("key")) {
			String[] stringsNum = new String[strings.length];
			for (int i = 0; i < strings.length; i++) {
				stringsNum[i] = strings[i].split(":")[0];
			}
			
			return stringsNum;
		} else if (type.equals("value")) {
			String[] stringsName = new String[strings.length];
			for (int i = 0; i < strings.length; i++) {
				stringsName[i] = strings[i].split(":")[1];
			}
			
			return stringsName;
		} else {
			return null;
		}
	}

}
