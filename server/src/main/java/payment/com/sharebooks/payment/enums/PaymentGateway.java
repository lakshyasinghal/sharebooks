package com.sharebooks.payment.enums;

public enum PaymentGateway {
	INSTAMOJO(1, "Instamojo"), CASHFREE(2, "Cashfree");

	private int id;
	private String desc;

	private PaymentGateway(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public PaymentGateway getById(int id) {
		switch (id) {
		case 1:
			return INSTAMOJO;
		case 2:
			return CASHFREE;
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
