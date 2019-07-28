package com.sharebooks.entities.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.string.StringSerializable;

public class City extends HelperEntity implements Comparable<City>, StringSerializable {

	private String name;
	private int stateId;

	public City() {
		// nothing
	}

	public City(int id, String name, int stateId) {
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
		super.serializeAsJson(jo);
		jo.put("name", name);
		jo.put("stateId", stateId);
	}

	@Override
	public String serializeAsString() {
		JSONObject jo = new JSONObject();
		try {
			serializeAsJson(jo);
		} catch (JsonSerializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo.toJSONString();
	}

	public String name() {
		return name;
	}

	public int stateId() {
		return stateId;
	}

}
