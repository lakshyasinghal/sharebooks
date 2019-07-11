package com.sharebooks.payment.enums;

public enum PaymentType {
	SUBSCRIPTION(1, "Subscription"), OTHER(2, "Buy Or Rent Book");

	private int id;
	private String desc;

	private PaymentType(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static PaymentType get(int id) {
		for (PaymentType type : PaymentType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return null;
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}
}
