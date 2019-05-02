package com.sharebooks.dtos;

import org.json.simple.JSONObject;

import com.sharebooks.coreEntities.User;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public class ResultUser implements JsonSerializable{
	private String uid;
	private String name;
	private String city;
	private String state;

	public ResultUser(){
		
	}
	
	public ResultUser(User user){
		this.uid = user.uid();
		this.name = user.name();
		this.city = user.city();
		this.state = user.state();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("uid", uid);
		jo.put("name", name);
		jo.put("city", city);
		jo.put("state", state);
	}

}
