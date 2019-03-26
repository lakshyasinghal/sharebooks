package com.sharebooks.response;

import java.util.List;
import org.json.simple.JSONObject;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;

public class JsonResponse implements Response {
	
	private final boolean isSuccessful;
	private final int statusCode;
	private final int errorCode;
	private final List<? extends Entity> list;
	
	public JsonResponse(){
		isSuccessful = false;
		statusCode = -1;
		errorCode = -1;
		list = null;
	}
	
	public JsonResponse(boolean isSuccessful , int statusCode , int errorCode , List<? extends Entity> list){
		this.isSuccessful = isSuccessful;
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.list = list;
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
			jo.put("isSuccessful", isSuccessful);
			jo.put("statusCode", statusCode);
			jo.put("errorCode", errorCode);
			jo.put("list", getSerializedList());
			
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	private String getSerializedList() throws JsonSerializationException{
		StringBuilder serList = new StringBuilder();
		if(list==null || list.size()==0){
			return "[]";
		}
		serList.append("[");
		int i=0;
		for(int len=list.size() ; i<len-1 ; i++){
			serList.append(list.get(i).serializeAsJson());
			serList.append(",");
		}
		serList.append(list.get(i).serializeAsJson());
		serList.append("]");
		return serList.toString();
	}
	
	
}
