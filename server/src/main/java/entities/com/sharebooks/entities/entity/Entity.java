package com.sharebooks.entities.entity;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonDeserializationException;
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
	
	//will return a map representation of the entity object
	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("id",id);
		return map;
	}
	
	public int id(){
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("id",id);
	}
	
	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException,Exception{
		
	}
}
