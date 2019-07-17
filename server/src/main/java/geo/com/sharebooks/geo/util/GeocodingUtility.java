package com.sharebooks.geo.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GeocodingUtility {

	public static String getLocationFromHttpRespJson(String json) throws Exception {
		Object obj = new JSONParser().parse(json);
		JSONObject jo = (JSONObject) obj;

		JSONArray results = (JSONArray) jo.get("results");
		JSONObject geometry = (JSONObject) ((JSONObject) results.get(0)).get("geometry");
		JSONObject location = (JSONObject) geometry.get("location");

		return location.toString();
	}
}
