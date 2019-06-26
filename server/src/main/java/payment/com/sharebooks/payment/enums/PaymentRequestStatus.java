package com.sharebooks.payment.enums;

public enum PaymentRequestStatus {
	PENDING(1, "Pending"), SENT(2, "Sent"), FAILED(3, "Failed"), COMPLETED(4, "Completed");

	private int id;
	private String desc;

	private PaymentRequestStatus(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public PaymentRequestStatus get(String desc) {
		for (PaymentRequestStatus status : PaymentRequestStatus.values()) {
			if (status.desc.toLowerCase().equals(desc.toLowerCase())) {
				return status;
			}
		}
		return null;
	}

	public PaymentRequestStatus get(int id) {
		for (PaymentRequestStatus status : PaymentRequestStatus.values()) {
			if (status.id == id) {
				return status;
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
