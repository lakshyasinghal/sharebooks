package com.sharebooks.geo.factory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.GeocodingProperties;
import com.sharebooks.geo.entities.Address;
import com.sharebooks.geo.entities.GeocodingRequest;
import com.sharebooks.geo.entities.GeocodingRequest.GeocodingRequestBuilder;

public class GeocodingRequestFactory {
	private static GeocodingRequestFactory instance;

	private GeocodingRequestFactory() {

	}

	public static GeocodingRequestFactory instance() {
		if (instance == null) {
			synchronized (GeocodingRequestFactory.class) {
				if (instance == null) {
					instance = new GeocodingRequestFactory();
				}
			}
		}

		return instance;
	}

	public GeocodingRequest createGeocodingRequest(Address address) {
		String api = AppConfig.geocodingProp(GeocodingProperties.API);
		String host = AppConfig.geocodingProp(GeocodingProperties.HOST);
		String api_key = AppConfig.geocodingProp(GeocodingProperties.API_KEY);
		GeocodingRequestBuilder builder = new GeocodingRequest.GeocodingRequestBuilder();
		builder.address(address).api(api).apiKey(api_key).host(host);
		return builder.build();
	}
}
