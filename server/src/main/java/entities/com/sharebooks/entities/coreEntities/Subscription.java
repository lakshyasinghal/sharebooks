package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.enums.Active;
import com.sharebooks.entities.coreEntities.enums.SubscriptionType;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public class Subscription extends CoreEntity implements JsonSerializable {

	private String userUid;
	private SubscriptionType type;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Active active;

	public Subscription(SubscriptionBuilder b) {
		userUid = b.userUid;
		type = b.type;
		startDateTime = b.startDateTime;
		endDateTime = b.endDateTime;
		active = b.active;
	}

	public static class SubscriptionBuilder extends CoreEntity.CoreEntityBuilder {
		private String userUid;
		private SubscriptionType type;
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
		private Active active;

		public SubscriptionBuilder() {

		}

		public SubscriptionBuilder userUid(String userUid) {
			this.userUid = userUid;
			return this;
		}

		public SubscriptionBuilder type(SubscriptionType type) {
			this.type = type;
			return this;
		}

		public SubscriptionBuilder startDateTime(LocalDateTime startDateTime) {
			this.startDateTime = startDateTime;
			return this;
		}

		public SubscriptionBuilder endDateTime(LocalDateTime endDateTime) {
			this.endDateTime = endDateTime;
			return this;
		}

		public SubscriptionBuilder active(Active active) {
			this.active = active;
			return this;
		}

		public Subscription build() {
			return new Subscription(this);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			super.serializeAsJson(jo);
			jo.put("userUid", userUid);
			jo.put("type", type.id());
			jo.put("startDateTime", JsonUtility.getJSONObjFromObj(startDateTime));
			jo.put("endDateTime", JsonUtility.getJSONObjFromObj(endDateTime));
			jo.put("active", active.id());
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("userUid", userUid);
		map.put("type", type.id());
		map.put("startDateTime", startDateTime.toString());
		map.put("endDateTime", endDateTime.toString());
		map.put("active", active.id());
		return map;
	}

	public String userUid() {
		return userUid;
	}

	public SubscriptionType type() {
		return type;
	}

	public LocalDateTime startDateTime() {
		return startDateTime;
	}

	public LocalDateTime endDateTime() {
		return endDateTime;
	}

	public Active active() {
		return active;
	}
}
