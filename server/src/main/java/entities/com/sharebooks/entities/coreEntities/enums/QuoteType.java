package com.sharebooks.entities.coreEntities.enums;

import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public enum QuoteType implements JsonSerializable{
	RENT(1, "Rent"), BUY(2, "Buy");

	private int id;
	private String description;

	private QuoteType(int id , String description){
		this.id = id;
		this.description = description;
	}

	public static QuoteType valueOf(int id) {
		switch (id) {
		case 1:
			return RENT;
		case 2:
			return BUY;
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
