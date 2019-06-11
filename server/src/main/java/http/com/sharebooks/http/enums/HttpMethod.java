package com.sharebooks.http.enums;

public enum HttpMethod {
	GET(1, "GET"), POST(2, "POST"), PUT(3, "PUT");

	private int id;
	private String desc;

	private HttpMethod(int id, String desc) {
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
