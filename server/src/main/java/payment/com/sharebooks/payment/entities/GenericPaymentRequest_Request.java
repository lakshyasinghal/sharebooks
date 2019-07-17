package com.sharebooks.payment.entities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.http.HttpClient;
import com.sharebooks.http.HttpClient.HttpClientBuilder;
import com.sharebooks.http.enums.ContentType;
import com.sharebooks.http.enums.Header;
import com.sharebooks.http.enums.HttpMethod;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.BooleanUtils;
import com.sharebooks.util.StringUtility;

public class GenericPaymentRequest_Request extends CoreEntity implements PaymentRequest_Request, JsonSerializable {

	private String api;
	private String amount;
	private String purpose;
	private String buyer_name;
	private String email;
	private String phone;
	private String redirect_url;
	private String webhook;
	private boolean allow_repeated_payments;
	private boolean send_email;
	private boolean send_sms;

	// these sensitive headers will not be a part of json or database table
	private String x_api_key;
	private String x_auth_token;

	public GenericPaymentRequest_Request() {

	}

	public GenericPaymentRequest_Request(GenericPaymentRequest_RequestBuilder b) {
		super(b);
		this.api = b.api;
		this.amount = b.amount;
		this.purpose = b.purpose;
		this.buyer_name = b.buyer_name;
		this.email = b.email;
		this.phone = b.phone;
		this.redirect_url = b.redirect_url;
		this.webhook = b.webhook;
		this.allow_repeated_payments = b.allow_repeated_payments;
		this.send_email = b.send_email;
		this.send_sms = b.send_sms;
		this.x_api_key = b.x_api_key;
		this.x_auth_token = b.x_auth_token;
	}

	public static class GenericPaymentRequest_RequestBuilder extends CoreEntity.CoreEntityBuilder {
		private String api;
		private String amount;
		private String purpose;
		private String buyer_name;
		private String email;
		private String phone;
		private String redirect_url;
		private String webhook;
		private boolean allow_repeated_payments;
		private boolean send_email;
		private boolean send_sms;

		private String x_api_key;
		private String x_auth_token;

		public GenericPaymentRequest_RequestBuilder() {

		}

		public GenericPaymentRequest_RequestBuilder api(String api) {
			this.api = api;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder amount(String amount) {
			this.amount = amount;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder purpose(String purpose) {
			this.purpose = purpose;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder buyerName(String buyer_name) {
			this.buyer_name = buyer_name;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder email(String email) {
			this.email = email;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder redirectUrl(String redirect_url) {
			this.redirect_url = redirect_url;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder webhook(String webhook) {
			this.webhook = webhook;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder allowRepeatedPayments(boolean allow_repeated_payments) {
			this.allow_repeated_payments = allow_repeated_payments;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder sendEmail(boolean send_email) {
			this.send_email = send_email;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder sendSMS(boolean send_sms) {
			this.send_sms = send_sms;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder xApiKey(String x_api_key) {
			this.x_api_key = x_api_key;
			return this;
		}

		public GenericPaymentRequest_RequestBuilder xAuthToken(String x_auth_token) {
			this.x_auth_token = x_auth_token;
			return this;
		}

		public GenericPaymentRequest_Request build() {
			return new GenericPaymentRequest_Request(this);
		}
	}

	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub

	}

	@Override
	public HttpClient serializeAsHttp() {
		HttpClientBuilder builder = new HttpClient.HttpClientBuilder();
		builder.contentType(ContentType.APPLICATION_FORM).isHttps(true).method(HttpMethod.POST)
				.parameters(getHttpParams()).headerMap(getHeaderMap()).url(api);
		return builder.build();
	}

	private Map<String, String> getHttpParams() {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("amount", amount);
		dataMap.put("purpose", purpose);
		if (!StringUtility.isEmptyOrNull(buyer_name)) {
			dataMap.put("buyer_name", buyer_name);
		}
		if (!StringUtility.isEmptyOrNull(email)) {
			dataMap.put("email", email);
		}
		if (!StringUtility.isEmptyOrNull(phone)) {
			dataMap.put("phone", phone);
		}
		if (!StringUtility.isEmptyOrNull(redirect_url)) {
			dataMap.put("redirect_url", redirect_url);
		}
		if (!StringUtility.isEmptyOrNull(webhook)) {
			dataMap.put("webhook", webhook);
		}
		dataMap.put("allow_repeated_payments", BooleanUtils.getString(allow_repeated_payments));
		dataMap.put("send_email", BooleanUtils.getString(send_email));
		dataMap.put("send_sms", BooleanUtils.getString(send_sms));

		return dataMap;
	}

	private Map<String, String> getHeaderMap() {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(Header.X_API_KEY.desc(), x_api_key);
		headerMap.put(Header.X_AUTH_TOKEN.desc(), x_auth_token);
		return headerMap;
	}

	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("api", api);
		map.put("amount", amount);
		map.put("purpose", purpose);
		map.put("buyer_name", buyer_name);
		map.put("email", email);
		map.put("phone", phone);
		map.put("redirect_url", redirect_url);
		map.put("webhook", webhook);
		map.put("allow_repeated_payments", allow_repeated_payments);
		map.put("send_email", send_email);
		map.put("send_sms", send_sms);

		return map;
	}

	public String api() {
		return api;
	}

	public String amount() {
		return amount;
	}

	public String purpose() {
		return purpose;
	}

	public String buyer_name() {
		return buyer_name;
	}

	public String email() {
		return email;
	}

	public String phone() {
		return phone;
	}

	public String redirect_url() {
		return redirect_url;
	}

	public String webhook() {
		return webhook;
	}

	public boolean allow_repeated_payments() {
		return allow_repeated_payments;
	}

	public boolean send_email() {
		return send_email;
	}

	public boolean send_sms() {
		return send_sms;
	}

}
