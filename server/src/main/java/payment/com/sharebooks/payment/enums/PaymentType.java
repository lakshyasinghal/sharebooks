package com.sharebooks.payment.enums;

public enum PaymentType {
	REGISTRATION(1, "User Registration"), OTHER(2, "Buy Or Rent Book");

	private int id;
	private String desc;

	private PaymentType(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static PaymentType get(int id) {
		switch (id) {
		case 1:
			return REGISTRATION;
		case 2:
			return OTHER;
		default:
			return null;
		}
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}
}
