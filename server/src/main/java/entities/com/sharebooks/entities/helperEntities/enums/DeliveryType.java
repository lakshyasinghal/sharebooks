package com.sharebooks.entities.helperEntities.enums;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;

public enum DeliveryType implements JsonSerializable,JsonDeserializable{
	
	HOME_DELIVERY(1,"Home delivery"),
	PICKUP(2,"Pickup");
	
	private int id;
	private String description;
	
	private DeliveryType(int id, String desc){
		this.id = id;
		this.description = desc;
	}
	
	public static DeliveryType valueOf(int id){
		DeliveryType dt = null;
		if(id==1){
			dt = DeliveryType.HOME_DELIVERY;
		}
		else if(id==2){
			dt = DeliveryType.PICKUP;
		}
		return dt;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		jo.put("id", id);
		jo.put("desc", description);
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return description;
	}

	@Override
	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException {
		// TODO Auto-generated method stub
		id = (int)(long)jo.get("id");
		description = (String)jo.get("desc");
	}

	
}
