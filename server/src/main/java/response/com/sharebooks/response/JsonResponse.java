package com.sharebooks.response;

import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;



public class JsonResponse implements Response {
	
	private final boolean success;
	private final int statusCode;
	private final int errorCode;
	//private final List<? extends Entity> list;
	private final Map<String,Object> map;
	
	public JsonResponse(){
		success = false;
		statusCode = -1;
		errorCode = -1;
		map = null;
	}
	
	public JsonResponse(boolean success , int statusCode , int errorCode , Map<String,Object> map){
		this.success = success;
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.map = map;
	}
	
	public String process() throws Exception {
		try{
			String json = serializeAsJson();
			return json;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("success", success);
			jo.put("statusCode", statusCode);
			jo.put("errorCode", errorCode);
			
			//jo.put("list", getSerializedList());
			//iterate over the map using key set and insert key and serialized value in JSON map 
			if(map!=null){
				for(String key : map.keySet()){
					Object obj = map.get(key);
					if(obj instanceof JsonSerializable){
						jo.put(key, JsonUtility.getJSONObjFromObj((JsonSerializable)obj));
					}
					else if(obj instanceof List){
						jo.put(key, JsonUtility.getJsonArrayFromList((List<JsonSerializable>)obj));
					}
					else if(obj instanceof String || obj instanceof Integer){
						jo.put(key, obj);
					}
					else{
						jo.put(key, obj);
					}
				}
			}
			
			
			return jo.toJSONString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
//	private String getSerializedList(List<Entity> list) throws JsonSerializationException{
//		StringBuilder serList = new StringBuilder();
//		if(list==null || list.size()==0){
//			return "[]";
//		}
//		serList.append("[");
//		int i=0;
//		for(int len=list.size() ; i<len-1 ; i++){
//			serList.append(list.get(i).serializeAsJson());
//			serList.append(",");
//		}
//		serList.append(list.get(i).serializeAsJson());
//		serList.append("]");
//		return serList.toString();
//	}
	
	
}
