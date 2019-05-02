package com.sharebooks.util;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public class JsonUtility {
	
	
//	public static String getSerializedList(List<? extends Object> list) throws JsonSerializationException{
//		StringBuilder serList = new StringBuilder();
//		if(list==null || list.size()==0){
//			return "[]";
//		}
//		serList.append("[");
//		int i=0;
//		Object obj = null;
//		for(int len=list.size() ; i<len ; i++){
//			obj = list.get(i);
//			if(obj instanceof Entity){
//				Entity entity = (Entity)obj;
//				serList.append(entity.serializeAsJson());
//				if(i<len-1){
//					serList.append(",");
//				}
//			}
//		}
//		serList.append("]");
//		return serList.toString();
//	}
	
	public static JSONObject getJSONObjFromObj(JsonSerializable jsonSerializable) throws JsonSerializationException{
		JSONObject jo = new JSONObject();
		jsonSerializable.serializeAsJson(jo);
		return jo;
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
