package com.sharebooks.http.enums;

public enum Header {
	CONTENT_TYPE(1, "Content-Type"), X_API_KEY(2, "X-Api-Key"), X_AUTH_TOKEN(3, "X-Auth-Token");

	private int id;
	private String desc;

	private Header(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}
}
