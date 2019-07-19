package com.sharebooks.urlShortener.entities;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.http.HttpClient;
import com.sharebooks.http.HttpClient.HttpClientBuilder;
import com.sharebooks.http.enums.ContentType;
import com.sharebooks.http.enums.HttpMethod;
import com.sharebooks.serialization.http.HttpSerializable;

public class ShortUrlRequest implements HttpSerializable {
	private String api;
	private String longUrl;
	private String access_token;
	private String format;

	public ShortUrlRequest(ShortUrlRequestBuilder b) {
		api = b.api;
		longUrl = b.longUrl;
		access_token = b.access_token;
		format = b.format;
	}

	public static class ShortUrlRequestBuilder {
		private String api;
		private String longUrl;
		private String access_token;
		private String format;

		public ShortUrlRequestBuilder api(String api) {
			this.api = api;
			return this;
		}

		public ShortUrlRequestBuilder longUrl(String longUrl) {
			this.longUrl = longUrl;
			return this;
		}

		public ShortUrlRequestBuilder accessToken(String access_token) {
			this.access_token = access_token;
			return this;
		}

		public ShortUrlRequestBuilder format(String format) {
			this.format = format;
			return this;
		}

		public ShortUrlRequest build() {
			return new ShortUrlRequest(this);
		}
	}

	@Override
	public HttpClient serializeAsHttp() {
		HttpClientBuilder builder = new HttpClient.HttpClientBuilder();
		builder.url(api).contentType(ContentType.APPLICATION_FORM).isHttps(true).method(HttpMethod.GET)
				.parameters(getHttpParams());
		return builder.build();
	}

	private Map<String, String> getHttpParams() {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("access_token", access_token);
		dataMap.put("format", format);
		dataMap.put("longUrl", longUrl);
		return dataMap;
	}
}
