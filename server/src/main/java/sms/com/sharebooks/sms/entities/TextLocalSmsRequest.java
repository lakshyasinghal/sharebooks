package com.sharebooks.sms.entities;

public class TextLocalSmsRequest extends SmsRequest {

	public TextLocalSmsRequest(TextLocalSMSRequestBuilder b) {
		super(b);
	}

	public static class TextLocalSMSRequestBuilder extends SMSRequestBuilder {

		public TextLocalSmsRequest build() {
			return new TextLocalSmsRequest(this);
		}
	}
}
