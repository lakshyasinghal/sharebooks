package com.sharebooks.payment.enums;

public enum GeocodingResponseStatus {
	OK("OK");

	private String desc;

	private GeocodingResponseStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
