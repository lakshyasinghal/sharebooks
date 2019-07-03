package com.sharebooks.payment.entities;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.payment.enums.PaymentStatus;
import com.sharebooks.serialization.json.JsonSerializable;

public class PaymentRequestWebhook implements JsonSerializable {
	private static Logger LOGGER = Logger.getLogger(PaymentRequestWebhook.class);

	private double amount;
	private String buyer;
	private String buyer_name;
	private String buyer_phone;
	private String currency;
	private double fees;
	private String longurl;
	private String mac;
	private String payment_id;
	private String payment_request_id;
	private String purpose;
	private String shorturl;
	private PaymentStatus status;

	public PaymentRequestWebhook(PaymentRequestWebhookBuilder b) {
		this.amount = b.amount;
		this.buyer = b.buyer;
		this.buyer_name = b.buyer_name;
		this.buyer_phone = b.buyer_phone;
		this.currency = b.currency;
		this.fees = b.fees;
		this.longurl = b.longurl;
		this.mac = b.mac;
		this.payment_id = b.payment_id;
		this.payment_request_id = b.payment_request_id;
		this.purpose = b.purpose;
		this.shorturl = b.shorturl;
		this.status = b.status;
	}

	public static class PaymentRequestWebhookBuilder {
		private double amount;
		private String buyer;
		private String buyer_name;
		private String buyer_phone;
		private String currency;
		private double fees;
		private String longurl;
		private String mac;
		private String payment_id;
		private String payment_request_id;
		private String purpose;
		private String shorturl;
		private PaymentStatus status;

		public PaymentRequestWebhookBuilder amount(double amount) {
			this.amount = amount;
			return this;
		}

		public PaymentRequestWebhookBuilder buyer(String buyer) {
			this.buyer = buyer;
			return this;
		}

		public PaymentRequestWebhookBuilder buyerName(String buyer_name) {
			this.buyer_name = buyer_name;
			return this;
		}

		public PaymentRequestWebhookBuilder buyerPhone(String buyer_phone) {
			this.buyer_phone = buyer_phone;
			return this;
		}

		public PaymentRequestWebhookBuilder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public PaymentRequestWebhookBuilder fees(double fees) {
			this.fees = fees;
			return this;
		}

		public PaymentRequestWebhookBuilder longurl(String longurl) {
			this.longurl = longurl;
			return this;
		}

		public PaymentRequestWebhookBuilder mac(String mac) {
			this.mac = mac;
			return this;
		}

		public PaymentRequestWebhookBuilder paymentId(String payment_id) {
			this.payment_id = payment_id;
			return this;
		}

		public PaymentRequestWebhookBuilder paymentRequestId(String payment_request_id) {
			this.payment_request_id = payment_request_id;
			return this;
		}

		public PaymentRequestWebhookBuilder purpose(String purpose) {
			this.purpose = purpose;
			return this;
		}

		public PaymentRequestWebhookBuilder shorturl(String shorturl) {
			this.shorturl = shorturl;
			return this;
		}

		public PaymentRequestWebhookBuilder status(PaymentStatus status) {
			this.status = status;
			return this;
		}

		public PaymentRequestWebhook build() {
			return new PaymentRequestWebhook(this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			// super.serializeAsJson(jo);
			jo.put("amount", amount);
			jo.put("buyer", buyer);
			jo.put("buyer_name", buyer_name);
			jo.put("buyer_phone", buyer_phone);
			jo.put("currency", currency);
			jo.put("fees", fees);
			jo.put("longurl", longurl);
			jo.put("mac", mac);
			jo.put("payment_id", payment_id);
			jo.put("payment_request_id", payment_request_id);
			jo.put("purpose", purpose);
			jo.put("shorturl", shorturl);
			jo.put("status", status.id());
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		// Map<String, Object> superMap = super.map();
		// map.putAll(superMap);

		map.put("amount", amount);
		map.put("buyer", buyer);
		map.put("buyer_name", buyer_name);
		map.put("buyer_phone", buyer_phone);
		map.put("currency", currency);
		map.put("fees", fees);
		map.put("longurl", longurl);
		map.put("mac", mac);
		map.put("payment_id", payment_id);
		map.put("payment_request_id", payment_request_id);
		map.put("purpose", purpose);
		map.put("shorturl", shorturl);
		map.put("status", status.id());

		return map;
	}

	public double amount() {
		return amount;
	}

	public String buyer() {
		return buyer;
	}

	public String buyerName() {
		return buyer_name;
	}

	public String buyerPhone() {
		return buyer_phone;
	}

	public String currency() {
		return currency;
	}

	public double fees() {
		return fees;
	}

	public String longurl() {
		return longurl;
	}

	public String mac() {
		return mac;
	}

	public String paymentId() {
		return payment_id;
	}

	public String paymentRequestId() {
		return payment_request_id;
	}

	public String purpose() {
		return purpose;
	}

	public String shorturl() {
		return shorturl;
	}

	public PaymentStatus status() {
		return status;
	}

}
