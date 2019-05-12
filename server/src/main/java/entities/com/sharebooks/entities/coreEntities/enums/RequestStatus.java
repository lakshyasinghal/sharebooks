package com.sharebooks.entities.coreEntities.enums;

import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public enum RequestStatus implements JsonSerializable{
	PENDING(1 , "Pending") , ACCEPTED(2 , "Accepted") , REJECTED(3 , "Rejected");
	
	private int id;
	private String description;
	
	private RequestStatus(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static RequestStatus valueOf(int id){
		switch(id){
		case 1:
			return PENDING;
		case 2:
			return ACCEPTED;
		case 3:
			return REJECTED;
		default:
			return null;
		}
	}
	
	public int id(){
		return id;
	}
	
	public String description(){
		return description;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		jo.put("id", id);
		jo.put("desc", description);
	}

}
