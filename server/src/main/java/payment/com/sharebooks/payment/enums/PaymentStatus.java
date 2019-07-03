package com.sharebooks.payment.enums;

public enum PaymentStatus {
	CREDIT(1, "Credit"), FAILED(2, "Failed");

	private int id;
	private String desc;

	private PaymentStatus(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static PaymentStatus get(String desc) {
		if (desc == null) {
			return null;
		} else if (desc.equals(CREDIT.desc())) {
			return CREDIT;
		} else if (desc.equals(FAILED.desc())) {
			return FAILED;
		} else {
			return null;
		}
	}

	public static PaymentStatus get(int id) {
		switch (id) {
		case 1:
			return CREDIT;
		case 2:
			return FAILED;
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
