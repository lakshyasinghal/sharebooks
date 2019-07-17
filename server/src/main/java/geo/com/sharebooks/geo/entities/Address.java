package com.sharebooks.geo.entities;

import com.sharebooks.serialization.string.StringSerializable;
import com.sharebooks.util.StringUtility;

public class Address implements StringSerializable {

	private String line1;
	private String line2;
	private String city;
	private String pincode;
	private String state;
	private String country;

	public Address() {

	}

	public Address(AddressBuilder b) {
		line1 = b.line1;
		line2 = b.line2;
		city = b.city;
		state = b.state;
		pincode = b.pincode;
		country = b.country;
	}

	public static class AddressBuilder {
		private String line1 = "";
		private String line2 = "";
		private String city = "";
		private String pincode = "";
		private String state = "";
		private String country = "";

		public AddressBuilder line1(String line1) {
			this.line1 = line1;
			return this;
		}

		public AddressBuilder line2(String line2) {
			this.line2 = line2;
			return this;
		}

		public AddressBuilder city(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder pincode(String pincode) {
			this.pincode = pincode;
			return this;
		}

		public AddressBuilder state(String state) {
			this.state = state;
			return this;
		}

		public AddressBuilder country(String country) {
			this.country = country;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}

	@Override
	public String serializeAsString() {
		StringBuilder sb = new StringBuilder();
		if (!StringUtility.isEmptyOrNull(line1)) {
			sb.append(line1 + ",");
		}
		if (!StringUtility.isEmptyOrNull(line2)) {
			sb.append(line2 + ",");
		}
		if (!StringUtility.isEmptyOrNull(pincode)) {
			sb.append(pincode + ",");
		}
		if (!StringUtility.isEmptyOrNull(city)) {
			sb.append(city + ",");
		}
		if (!StringUtility.isEmptyOrNull(state)) {
			sb.append(state + ",");
		}
		if (!StringUtility.isEmptyOrNull(country)) {
			sb.append(country + ",");
		}

		String addressStr = StringUtility.removeCommaFromEnd(sb.toString().trim());

		return addressStr;
	}

	public String line1() {
		return line1;
	}

	public String line2() {
		return line2;
	}

	public String city() {
		return city;
	}

	public String state() {
		return state;
	}

	public String pincode() {
		return pincode;
	}

	public String country() {
		return country;
	}
}
