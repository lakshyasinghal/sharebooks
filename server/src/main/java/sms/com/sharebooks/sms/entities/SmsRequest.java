package com.sharebooks.sms.entities;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.http.HttpClient;
import com.sharebooks.http.HttpClient.HttpClientBuilder;
import com.sharebooks.http.enums.ContentType;
import com.sharebooks.http.enums.HttpMethod;
import com.sharebooks.serialization.http.HttpSerializable;

public abstract class SmsRequest implements HttpSerializable {

	private String api;
	private String sender;
	private String numbers;
	private String message;
	private String apiKey;

	public SmsRequest() {
	}

	public SmsRequest(SMSRequestBuilder b) {
		api = b.api;
		sender = b.sender;
		numbers = b.numbers;
		message = b.message;
		apiKey = b.apiKey;
	}

	public static class SMSRequestBuilder {
		private String api;
		private String sender;
		private String numbers;
		private String message;
		private String apiKey;

		public SMSRequestBuilder api(String api) {
			this.api = api;
			return this;
		}

		public SMSRequestBuilder sender(String sender) {
			this.sender = sender;
			return this;
		}

		public SMSRequestBuilder numbers(String numbers) {
			this.numbers = numbers;
			return this;
		}

		public SMSRequestBuilder message(String message) {
			this.message = message;
			return this;
		}

		public SMSRequestBuilder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
	}

	@Override
	public HttpClient serializeAsHttp() {
		HttpClientBuilder builder = new HttpClient.HttpClientBuilder();
		builder.url(api).contentType(ContentType.APPLICATION_FORM).isHttps(true).method(HttpMethod.POST)
				.parameters(getHttpParams());
		return builder.build();
	}

	private Map<String, String> getHttpParams() {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("sender", sender);
		dataMap.put("numbers", numbers);
		dataMap.put("message", message);
		dataMap.put("apiKey", apiKey);
		return dataMap;
	}

	private Map<String, String> getHeaderMap() {
		return null;
	}

	public String api() {
		return api;
	}

	public String sender() {
		return sender;
	}

	public String numbers() {
		return numbers;
	}

	public String message() {
		return message;
	}

	public String apiKey() {
		return apiKey;
	}
}
