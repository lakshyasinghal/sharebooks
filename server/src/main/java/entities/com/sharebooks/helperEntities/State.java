package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;
import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class State extends HelperEntity implements Comparable<State>{
	
	private int id;
	private String name;
	
	public State(){
		//nothing
	}
	
	public State(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int compareTo(State state) {
		return name.compareTo(state.name());
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
	
	public int id(){
		return id;
	}
	
	public String name(){
		return name;
	}
}
