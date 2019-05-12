package com.sharebooks.entities.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class City extends HelperEntity implements Comparable<City>{
	
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
	
	@Override
	public int compareTo(City city) {
		return name.compareTo(city.name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("id", id);
		jo.put("name", name);
		jo.put("stateId", stateId);
	}
	
	public String name(){
		return name;
	}

	public int stateId(){
		return stateId;
	}

}
