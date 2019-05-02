package com.sharebooks.entity;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public abstract class Entity implements JsonSerializable{
	protected int id;
	
	public Entity(){
		id = -1;
	}
	
	public Entity(int id){
		this.id = id;
	}
	
	public int id(){
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("id",id);
	}
}
