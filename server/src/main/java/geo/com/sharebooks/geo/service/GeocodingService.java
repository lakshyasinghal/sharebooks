package com.sharebooks.geo.service;

import com.sharebooks.geo.entities.Address;
import com.sharebooks.geo.entities.GeoCoordinates;
import com.sharebooks.geo.entities.GeocodingRequest;
import com.sharebooks.geo.factory.GeoCoordinatesFactory;
import com.sharebooks.geo.factory.GeocodingRequestFactory;
import com.sharebooks.geo.util.DistanceCalculator;
import com.sharebooks.geo.util.GeocodingUtility;
import com.sharebooks.http.HttpClient;
import com.sharebooks.http.service.HttpService;

public class GeocodingService {
	private static GeocodingService instance;
	private GeocodingRequestFactory geocodingRequestFactory = GeocodingRequestFactory.instance();
	private HttpService httpService = HttpService.instance();

	private GeocodingService() {

	}

	public static GeocodingService instance() {
		if (instance == null) {
			synchronized (GeocodingService.class) {
				if (instance == null) {
					instance = new GeocodingService();
				}
			}
		}

		return instance;
	}

	public GeoCoordinates getCoordinatesFromAddress(Address address) throws Exception {
		// get GeocodingRequest instance
		GeocodingRequest geocodingReq = geocodingRequestFactory.createGeocodingRequest(address);

		// get serialized http client
		HttpClient client = geocodingReq.serializeAsHttp();

		// make request and get response which will be in json format
		String data = httpService.makeRequest(client);

		// get the geo coordinates from json response
		GeoCoordinates coordinates = GeoCoordinatesFactory.instance()
				.createFromJson(GeocodingUtility.getLocationFromHttpRespJson(data));
		return coordinates;
	}

	public double getDistanceFromLatLng(GeoCoordinates point1, GeoCoordinates point2) {
		return DistanceCalculator.calculateFromCoordinates(point1, point2);
	}

}
