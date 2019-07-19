package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.util.dateTime.LocalDateTime;

public class PasswordResetLink extends CoreEntity {

	private String userUid;
	private String longUrl;
	private String shortUrl;
	private LocalDateTime generatedAt;
	private LocalDateTime expiryTime;
	private boolean isExpired;

	public PasswordResetLink() {

	}

	public PasswordResetLink(PasswordResetLinkBuilder b) {
		userUid = b.userUid;
		longUrl = b.longUrl;
		shortUrl = b.shortUrl;
		generatedAt = b.generatedAt;
		expiryTime = b.expiryTime;
		isExpired = b.isExpired;
	}

	public static class PasswordResetLinkBuilder extends CoreEntityBuilder {
		private String userUid;
		private String longUrl;
		private String shortUrl;
		private LocalDateTime generatedAt = new LocalDateTime();
		private LocalDateTime expiryTime;
		private boolean isExpired = false;

		public PasswordResetLinkBuilder userUid(String userUid) {
			this.userUid = userUid;
			return this;
		}

		public PasswordResetLinkBuilder longUrl(String longUrl) {
			this.longUrl = longUrl;
			return this;
		}

		public PasswordResetLinkBuilder shortUrl(String shortUrl) {
			this.shortUrl = shortUrl;
			return this;
		}

		public PasswordResetLinkBuilder expiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
			return this;
		}

		public PasswordResetLink build() {
			return new PasswordResetLink(this);
		}
	}

	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("userUid", userUid);
		map.put("longUrl", longUrl);
		map.put("shortUrl", shortUrl);
		map.put("generatedAt", generatedAt.toString());
		map.put("expiryTime", expiryTime.toString());
		map.put("isExpired", isExpired);
		return map;
	}

	public String userUid() {
		return userUid;
	}

	public String longUrl() {
		return longUrl;
	}

	public String shortUrl() {
		return shortUrl;
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
