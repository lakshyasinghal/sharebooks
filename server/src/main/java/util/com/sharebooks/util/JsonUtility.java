package com.sharebooks.util;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;

public class JsonUtility {

	
	public static String getJSONFromObj(JsonSerializable jsonSerializable) throws JsonSerializationException{
		JSONObject jo = new JSONObject();
		jsonSerializable.serializeAsJson(jo);
		return jo.toJSONString();
	} 
	
	public static JSONObject getJSONObjFromObj(JsonSerializable jsonSerializable) throws JsonSerializationException{
		JSONObject jo = new JSONObject();
		jsonSerializable.serializeAsJson(jo);
		return jo;
	}
	
	
	public static JsonDeserializable getDeserializedObjectFromJson(JsonDeserializable jdObj, String json) throws Exception{
		if(jdObj==null){
			throw new RuntimeException("jdObj cannot be null.");
		}
		Object obj = new JSONParser().parse(json);
		JSONObject jo = (JSONObject) obj;
		jdObj.deserializeFromJson(jo);

		return jdObj;
	} 
	
	public static JsonDeserializable getDeserializedObjectFromJsonObject(JsonDeserializable jdObj, JSONObject jo) throws Exception{
		if(jdObj==null){
			throw new RuntimeException("jdObj cannot be null.");
		}
		jdObj.deserializeFromJson(jo);
		return jdObj;
	} 

	
	@SuppressWarnings("unchecked")
	public static JSONArray getJsonArrayFromList(List<? extends JsonSerializable> list) throws JsonSerializationException{
		JSONArray array = new JSONArray();
		if(list!=null){
			for(JsonSerializable js: list){
				JSONObject jo = new JSONObject();
				js.serializeAsJson(jo);
				array.add(jo);
			}
		}
		
		return array;
	}
	
}
