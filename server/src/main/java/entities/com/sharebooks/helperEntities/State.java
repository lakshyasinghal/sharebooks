package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;

public class State extends Entity{
	
	private String name;
	
	public State(){
		//nothing
	}
	
	public State(String name){
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id" , id);
			jo.put("name" , name);
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}


	public static State deserializeFromJson(JSONObject jo) {
		State state = new State();
		state.id = (long)jo.get("id");
		state.name = (String)jo.get("name");
		
		return state;
	}
	
	public String name(){
		return name;
	}
}
