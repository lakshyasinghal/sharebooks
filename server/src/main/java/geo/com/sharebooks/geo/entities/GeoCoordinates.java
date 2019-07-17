package com.sharebooks.geo.entities;

public class GeoCoordinates {

	private double latitude;
	private double longitude;

	public GeoCoordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double lat() {
		return latitude;
	}

	public double lng() {
		return longitude;
	}
}
