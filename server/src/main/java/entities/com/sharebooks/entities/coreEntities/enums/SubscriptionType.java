package com.sharebooks.entities.coreEntities.enums;

public enum SubscriptionType {
	MONTHLY(1), QUARETERLY(2), YEARLY(3), LIFETIME(4);

	private int id;

	private SubscriptionType(int id) {
		this.id = id;
	}

	public static SubscriptionType get(int id) {
		for (SubscriptionType type : SubscriptionType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return null;
	}

	public int id() {
		return id;
	}
}
