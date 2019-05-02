package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;
import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class State extends HelperEntity implements Comparable<State>{
	
	private String name;
	
	public State(){
		//nothing
	}
	
	public State(int id, String name){
		super(id);
		this.name = name;
	}
	
	@Override
	public int compareTo(State state) {
		return name.compareTo(state.name());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {	
		jo.put("id" , id);
		jo.put("name" , name);
	}
	
	
	public String name(){
		return name;
	}
}
