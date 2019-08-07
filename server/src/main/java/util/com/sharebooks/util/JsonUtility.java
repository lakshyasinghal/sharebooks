package com.sharebooks.util;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;

public class JsonUtility {

	public static String getJSONFromObj(JsonSerializable jsonSerializable) throws JsonSerializationException {
		JSONObject jo = new JSONObject();
		jsonSerializable.serializeAsJson(jo);
		return jo.toJSONString();
	}

	public static JSONObject getJSONObjFromObj(JsonSerializable jsonSerializable) throws JsonSerializationException {
		JSONObject jo = new JSONObject();
		jsonSerializable.serializeAsJson(jo);
		return jo;
	}

	public static JsonDeserializable getDeserializedObjectFromJson(JsonDeserializable jdObj, String json)
			throws Exception {
		if (jdObj == null) {
			throw new RuntimeException("jdObj cannot be null.");
		}
		Object obj = new JSONParser().parse(json);
		JSONObject jo = (JSONObject) obj;
		jdObj.deserializeFromJson(jo);

		return jdObj;
	}

	public static JsonDeserializable getDeserializedObjectFromJsonObject(JsonDeserializable jdObj, JSONObject jo)
			throws Exception {
		if (jdObj == null) {
			throw new RuntimeException("jdObj cannot be null.");
		}
		jdObj.deserializeFromJson(jo);
		return jdObj;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray getJsonArrayFromList(List<? extends JsonSerializable> list)
			throws JsonSerializationException {
		JSONArray array = new JSONArray();
		if (list != null) {
			for (JsonSerializable js : list) {
				JSONObject jo = new JSONObject();
				js.serializeAsJson(jo);
				array.add(jo);
			}
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray getJsonArrayFromJsonString(String jsonString)
			throws JsonSerializationException, ParseException {
		Object obj = new JSONParser().parse(jsonString);
		JSONArray array = (JSONArray) obj;
		return array;
	}

	public static int getIntValueFromJsonObject(JSONObject jo, String field, long backupVal) {

		int value = (int) (long) (jo.get("accountType") != null ? jo.get("accountType") : backupVal);
		return value;
	}

}
