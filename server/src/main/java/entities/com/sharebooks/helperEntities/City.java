package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;

public class City extends Entity {
	private String name;
	private int stateId;
	
	public City(){
		//nothing
	}
	
	public City(int id , String name , int stateId){
		super(id);
		this.name = name;
		this.stateId = stateId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("name", name);
			jo.put("stateId", stateId);
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	
	public static City deserializeFromJson(JSONObject jo) {
		City city = new City();
		city.id = (long)jo.get("id");
		city.name = (String)jo.get("name");
		city.stateId = (int)(long)jo.get("stateId");
		
		return city;
	}
	
	public String name(){
		return name;
	}

	public int stateId(){
		return stateId;
	}
}
