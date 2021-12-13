package com.manudev90.userapi.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static String objectToJson(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
	}

}
