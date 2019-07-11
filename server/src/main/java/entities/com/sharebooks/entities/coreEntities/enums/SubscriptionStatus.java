package com.sharebooks.entities.coreEntities.enums;

public enum SubscriptionStatus {
	PENDING(1), SUBSCRIBED(2), EXPIRED(3), RENEWED(4);

	private int id;

	private SubscriptionStatus(int id) {
		this.id = id;
	}

	public static SubscriptionStatus get(int id) {
		for (SubscriptionStatus status : SubscriptionStatus.values()) {
			if (status.id == id) {
				return status;
			}
		}
		return null;
	}

	public int id() {
		return id;
	}
}
