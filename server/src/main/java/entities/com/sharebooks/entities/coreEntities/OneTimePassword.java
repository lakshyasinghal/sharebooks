package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.dateTime.LocalDateTime;

public class OneTimePassword extends CoreEntity implements JsonSerializable {

	private String userUid;
	private String otp;
	private boolean accepted;
	private int attempts;
	private LocalDateTime generatedAt;
	private LocalDateTime expiryTime;
	private boolean isExpired;

	public OneTimePassword() {

	}

	public OneTimePassword(OneTimePasswordBuilder b) {
		super(b);
		this.userUid = b.userUid;
		this.otp = b.otp;
		this.accepted = b.accepted;
		this.attempts = b.attempts;
		this.generatedAt = b.generatedAt;
		this.expiryTime = b.expiryTime;
		this.isExpired = b.isExpired;
	}

	public static class OneTimePasswordBuilder extends CoreEntityBuilder {
		private String userUid;
		private String otp;
		private boolean accepted;
		private int attempts;
		private LocalDateTime generatedAt;
		private LocalDateTime expiryTime;
		private boolean isExpired;

		public OneTimePasswordBuilder userUid(String userUid) {
			this.userUid = userUid;
			return this;
		}

		public OneTimePasswordBuilder otp(String otp) {
			this.otp = otp;
			return this;
		}

		public OneTimePasswordBuilder accepted(boolean accepted) {
			this.accepted = accepted;
			return this;
		}

		public OneTimePasswordBuilder attempts(int attempts) {
			this.attempts = attempts;
			return this;
		}

		public OneTimePasswordBuilder generatedAt(LocalDateTime generatedAt) {
			this.generatedAt = generatedAt;
			return this;
		}

		public OneTimePasswordBuilder expiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
			return this;
		}

		public OneTimePasswordBuilder isExpired(boolean isExpired) {
			this.isExpired = isExpired;
			return this;
		}

		public OneTimePassword build() {
			return new OneTimePassword(this);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			// not required as of now
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	// will return a map representing the otp
	// will be mostly used when inserting new otp or updating existing one into
	// database
	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("userUid", userUid);
		map.put("otp", otp);
		map.put("accepted", accepted);
		map.put("attempts", attempts);
		map.put("generatedAt", generatedAt.toString());
		map.put("expiryTime", expiryTime.toString());
		map.put("isExpired", isExpired);
		return map;
	}

	public String uesrUid() {
		return userUid;
	}

	public String otp() {
		return otp;
	}

	public boolean accepted() {
		return accepted;
	}

	public int attempts() {
		return attempts;
	}

	public LocalDateTime generatedAt() {
		return generatedAt;
	}

	public LocalDateTime expiryTime() {
		return expiryTime;
	}

	public boolean isExpired() {
		return isExpired;
	}
}
