package com.sharebooks.entities.helperEntities;

import org.json.simple.JSONObject;

import com.sharebooks.entities.helperEntities.enums.DeliveryType;
import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.dateTime.LocalDate;

public class RentInfo implements JsonSerializable, JsonDeserializable {
	private int requiredDays;
	private int rentAmount;
	private int totalRent;
	private LocalDate startDate;
	private DeliveryType deliveryType;
	private int deliveryCharges;

	public RentInfo() {

	}

	public RentInfo(int requiredDays, int rentAmount, int totalRent, LocalDate startDate, DeliveryType deliveryType,
			int deliveryCharges) {
		this.requiredDays = requiredDays;
		this.rentAmount = rentAmount;
		this.totalRent = totalRent;
		this.startDate = startDate;
		this.deliveryType = deliveryType;
		this.deliveryCharges = deliveryCharges;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		jo.put("requiredDays", requiredDays);
		jo.put("rentAmount", rentAmount);
		jo.put("totalRent", totalRent);
		jo.put("startDate", startDate.toString());
//		JSONObject deliveryTypeJO = new JSONObject();
//		deliveryType.serializeAsJson(deliveryTypeJO);
		jo.put("deliveryType", deliveryType.id());
		jo.put("deliveryCharges", deliveryCharges);
	}

	@Override
	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException {
		requiredDays = jo.get("requiredDays") != null ? (int)(long) jo.get("requiredDays") : -1;
		rentAmount = jo.get("rentAmount") != null ? (int) (long)jo.get("rentAmount") : -1;
		totalRent = jo.get("totalRent") != null ? (int)(long) jo.get("totalRent") : -1;
		startDate = LocalDate.buildFromString((String) jo.get("startDate"));
		deliveryType = jo.get("deliveryType") != null ? DeliveryType.valueOf((int)(long) jo.get("deliveryType")) : null;
		deliveryCharges = jo.get("deliveryCharges") != null ? (int)(long) jo.get("deliveryCharges") : -1;
	}

	public int requiredDays() {
		return requiredDays;
	}

	public int rentAmount() {
		return rentAmount;
	}

	public int totalRent() {
		return totalRent;
	}

	public LocalDate startDate() {
		return startDate;
	}

	public DeliveryType deliveryType() {
		return deliveryType;
	}

	public int deliveryCharges() {
		return deliveryCharges;
	}
}
