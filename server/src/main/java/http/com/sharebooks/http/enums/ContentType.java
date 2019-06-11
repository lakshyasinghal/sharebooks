package com.sharebooks.http.enums;

public enum ContentType {
	JSON(1, "application/json"), APPLICATION_FORM(2, "Application form");

	private int id;
	private String desc;

	private ContentType(int id, String desc) {
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
