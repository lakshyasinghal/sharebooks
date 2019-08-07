package com.sharebooks.sources.enums;

public enum ServiceType {
	BOOK("book"), USER("user"), BOOK_REQUEST("bookRequest"), NOTIFICATION("notification"), ORDER("order"),
	QUOTE("quote"), RESULT("result"), CITY("city"), STATE("state"), BOOK_CATEGORY("bookCategory"), OTP("otp"),
	PAYMENT("payment"), GEOCODING("geocoding"), SMS("sms");

	private String desc;

	private ServiceType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
