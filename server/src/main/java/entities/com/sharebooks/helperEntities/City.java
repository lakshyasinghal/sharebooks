package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entity.Entity;
import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class City extends HelperEntity implements Comparable<City>{
	
	private int id;
	private String name;
	private int stateId;
	
	public City(){
		//nothing
	}
	
	public City(int id , String name , int stateId){
		this.id = id;
		this.name = name;
		this.stateId = stateId;
	}
	
	@Override
	public int compareTo(City city) {
		return name.compareTo(city.name);
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
	
	public String name(){
		return name;
	}

	public int stateId(){
		return stateId;
	}

}
