package com.sharebooks.entities.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.string.StringSerializable;

public class State extends HelperEntity implements Comparable<State>, StringSerializable {

	private String name;

	public State() {
		// nothing
	}

	public State(int id, String name) {
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
		super.serializeAsJson(jo);
		jo.put("name", name);
	}

	@Override
	public String serializeAsString() throws Exception {
		JSONObject jo = new JSONObject();
		serializeAsJson(jo);
		return jo.toJSONString();
	}

	public String name() {
		return name;
	}

}
