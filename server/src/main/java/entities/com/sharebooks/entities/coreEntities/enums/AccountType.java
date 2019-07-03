package com.sharebooks.entities.coreEntities.enums;

public enum AccountType {
	UNREGISTERED(0), REGISTERED(1);

	private int id;

	private AccountType(int id) {
		this.id = id;
	}

	public static AccountType get(int id) {
		for (AccountType accountType : AccountType.values()) {
			if (accountType.id() == id) {
				return accountType;
			}
		}
		return null;
	}

	public int id() {
		return id;
	}
}
