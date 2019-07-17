package com.sharebooks.sms.factory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.SmsProperties;
import com.sharebooks.sms.entities.TextLocalSmsRequest;
import com.sharebooks.sms.entities.TextLocalSmsRequest.TextLocalSMSRequestBuilder;

public class TextLocalSmsRequestFactory {
	private static TextLocalSmsRequestFactory instance = null;

	private TextLocalSmsRequestFactory() {

	}

	public static TextLocalSmsRequestFactory instance() {
		if (instance == null) {
			synchronized (TextLocalSmsRequestFactory.class) {
				if (instance == null) {
					instance = new TextLocalSmsRequestFactory();
				}
			}
		}

		return instance;
	}

	public TextLocalSmsRequest create(String message, String numbers) {
		String api = AppConfig.smsProp(SmsProperties.TEXTLOCAL_API);
		String apiKey = AppConfig.smsProp(SmsProperties.TEXTLOCAL_API_KEY);
		String sender = AppConfig.smsProp(SmsProperties.TEXTLOCAL_SENDER);

		TextLocalSMSRequestBuilder b = new TextLocalSmsRequest.TextLocalSMSRequestBuilder();
		b.api(api).apiKey(apiKey).sender(sender).message(message).numbers(numbers);
		return b.build();
	}
}
