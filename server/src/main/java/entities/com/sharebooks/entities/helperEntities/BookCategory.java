package com.sharebooks.entities.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.HelperEntity;
import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.string.StringSerializable;

public final class BookCategory extends HelperEntity implements Comparable<BookCategory>, StringSerializable {

	private String category;

	public BookCategory() {

	}

	public BookCategory(int id, String category) {
		super(id);
		this.category = category;
	}

	@Override
	public int compareTo(BookCategory bookCategory) {
		return category.compareTo(bookCategory.category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			super.serializeAsJson(jo);
			jo.put("category", category);
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	@Override
	public String serializeAsString() throws Exception {
		JSONObject jo = new JSONObject();
		serializeAsJson(jo);
		return jo.toJSONString();
	}

	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException, Exception {
		super.deserializeFromJson(jo);
		category = (String) jo.get("name");
	}

	public String category() {
		return category;
	}
}
