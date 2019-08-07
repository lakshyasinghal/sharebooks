package com.sharebooks.entities.entity;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;

public abstract class Entity implements JsonSerializable, JsonDeserializable {
	protected int id;

	public Entity() {
		id = -1;
	}

	public Entity(int id) {
		this.id = id;
	}

	public Entity(EntityBuilder b) {
		this.id = b.id;
	}

	public static class EntityBuilder {
		private int id;

		public EntityBuilder() {

		}

		public EntityBuilder id(int id) {
			this.id = id;
			return this;
		}
	}

	// will return a map representation of the entity object
	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return map;
	}

	public int id() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("id", id);
	}

	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException, Exception {
		id = (int) jo.get("id");
	}
}
