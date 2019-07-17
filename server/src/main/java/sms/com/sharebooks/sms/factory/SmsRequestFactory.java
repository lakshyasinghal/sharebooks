package com.sharebooks.sms.factory;

import com.sharebooks.sms.entities.SmsRequest;
import com.sharebooks.sms.enums.SmsProvider;

public class SmsRequestFactory {
	private static SmsRequestFactory instance = null;
	private TextLocalSmsRequestFactory txtLocalFactory = TextLocalSmsRequestFactory.instance();

	private SmsRequestFactory() {

	}

	public static SmsRequestFactory instance() {
		if (instance == null) {
			synchronized (SmsRequestFactory.class) {
				if (instance == null) {
					instance = new SmsRequestFactory();
				}
			}
		}

		return instance;
	}

	public SmsRequest create(SmsProvider smsProvider, String message, String numbers) {
		SmsRequest object = null;

		if (smsProvider == SmsProvider.TEXTLOCAL) {
			object = txtLocalFactory.create(message, numbers);
		}
		return object;
	}
}
