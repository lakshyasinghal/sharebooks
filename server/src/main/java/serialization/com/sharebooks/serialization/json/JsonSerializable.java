package com.sharebooks.serialization.json;

import org.json.simple.JSONObject;

//import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;

public interface JsonSerializable {
	
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException;
} 
