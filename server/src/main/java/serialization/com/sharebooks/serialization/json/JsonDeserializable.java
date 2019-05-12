package com.sharebooks.serialization.json;

import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonDeserializationException;


public interface JsonDeserializable {
	
	public abstract void deserializeFromJson(JSONObject jo) throws JsonDeserializationException,Exception;
}
