package com.sharebooks.geo.factory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.geo.entities.GeoCoordinates;

public class GeoCoordinatesFactory {
	private static GeoCoordinatesFactory instance;

	private GeoCoordinatesFactory() {

	}

	public static GeoCoordinatesFactory instance() {
		if (instance == null) {
			synchronized (GeoCoordinatesFactory.class) {
				if (instance == null) {
					instance = new GeoCoordinatesFactory();
				}
			}
		}

		return instance;
	}

	public GeoCoordinates createFromJson(String json) throws Exception {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject) obj;

			double lat = (double) jo.get("lat");
			double lng = (double) jo.get("lng");

			return new GeoCoordinates(lat, lng);
		} catch (ParseException ex) {
			throw ex;
		}
	}
}
