package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class Preference extends HelperEntity{
	
	private String category;
	
	public Preference(String category){
		this.category = category;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try{
			jo.put("category", category);
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	public String category(){
		return category;
	}
}
