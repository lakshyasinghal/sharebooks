package com.sharebooks.geo.entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.http.HttpClient;
import com.sharebooks.http.HttpClient.HttpClientBuilder;
import com.sharebooks.http.enums.ContentType;
import com.sharebooks.http.enums.HttpMethod;
import com.sharebooks.serialization.http.HttpSerializable;
import com.sharebooks.serialization.json.JsonSerializable;

public class GeocodingRequest implements JsonSerializable, HttpSerializable {

	private String api;
	private Address address;

	private String host;
	private String api_key;

	public GeocodingRequest(GeocodingRequestBuilder b) {
		api = b.api;
		address = b.address;
		host = b.host;
		api_key = b.api_key;
	}

	public static class GeocodingRequestBuilder {
		private String api;
		private Address address;
		private String host;
		private String api_key;

		public GeocodingRequestBuilder api(String api) {
			this.api = api;
			return this;
		}

		public GeocodingRequestBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public GeocodingRequestBuilder host(String host) {
			this.host = host;
			return this;
		}

		public GeocodingRequestBuilder apiKey(String api_key) {
			this.api_key = api_key;
			return this;
		}

		public GeocodingRequest build() {
			return new GeocodingRequest(this);
		}
	}

	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
	}

	@Override
	public HttpClient serializeAsHttp() {
		HttpClientBuilder builder = new HttpClient.HttpClientBuilder();
		builder.url(api).contentType(ContentType.APPLICATION_FORM).isHttps(true).method(HttpMethod.GET)
				.parameters(getHttpParams()).headerMap(getHeaderMap());
		return builder.build();
	}

	private Map<String, String> getHttpParams() {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("address", address.serializeAsString());

		return dataMap;
	}

	private Map<String, String> getHeaderMap() {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("X-RapidAPI-Host", host);
		headerMap.put("X-RapidAPI-Key", api_key);
		return headerMap;
	}

}
