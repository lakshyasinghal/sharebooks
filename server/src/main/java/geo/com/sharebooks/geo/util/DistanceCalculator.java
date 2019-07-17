package com.sharebooks.geo.util;

import com.sharebooks.geo.entities.GeoCoordinates;

public class DistanceCalculator {

	public static double calculateFromCoordinates(GeoCoordinates point1, GeoCoordinates point2) {
		double R = 6371; // Radius of the earth in km
		double dLat = (point2.lat() - point1.lat()) * (Math.PI / 180); // deg2rad below
		double dLon = (point2.lng() - point1.lng()) * (Math.PI / 180);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(point1.lat()))
				* Math.cos(deg2rad(point2.lat())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c; // Distance in km
		return d;
	}

	private static double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}

//	public static void main(String[] args) {
//		GeoCoordinates point1 = new GeoCoordinates(28.3899664, 77.2979782);
//		GeoCoordinates point2 = new GeoCoordinates(28.3733754, 77.335314);
//
//		System.out.print(calculateFromCoordinates(point1, point2));
//	}
}
