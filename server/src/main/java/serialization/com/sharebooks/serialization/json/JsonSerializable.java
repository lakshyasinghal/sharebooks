package com.sharebooks.serialization.json;

//import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;

public interface JsonSerializable {
	
	public String serializeAsJson() throws JsonSerializationException;
	//public void deserializeFromJson(JSONObject obj);
} 
