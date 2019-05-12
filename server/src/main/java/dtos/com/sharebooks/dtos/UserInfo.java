package com.sharebooks.dtos;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public class UserInfo implements JsonSerializable{
	private String uid;
	private String name;
	private String city;
	private String state;

	public UserInfo(){
		
	}
	
	public UserInfo(User user){
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
