package com.sharebooks.entities.coreEntities.enums;

import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public enum QuoteStatus implements JsonSerializable{
	INITIALIZED(1, "Initialized"), CONFIRMED(2, "Confirmed");

	private int id;
	private String description;

	private QuoteStatus(int id , String description){
		this.id = id;
		this.description = description;
	}

	public static QuoteStatus valueOf(int id) {
		switch (id) {
		case 1:
			return INITIALIZED;
		case 2:
			return CONFIRMED;
		default:
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		jo.put("id", id);
		jo.put("desc", description);
	}

	public int id() {
		return id;
	}

	public String description() {
		return description;
	}
}
