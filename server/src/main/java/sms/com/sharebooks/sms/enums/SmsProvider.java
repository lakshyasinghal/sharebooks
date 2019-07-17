package com.sharebooks.sms.enums;

public enum SmsProvider {
	TEXTLOCAL(1);

	private int id;

	private SmsProvider(int id) {
		this.id = id;
	}

	public static SmsProvider get(int id) {
		for (SmsProvider provider : SmsProvider.values()) {
			if (provider.id == id) {
				return provider;
			}
		}

		return null;
	}

	public int id() {
		return id;
	}
}
