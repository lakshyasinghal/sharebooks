package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;

public class PaymentRequest extends CoreEntity implements JsonSerializable{
	
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
	
	
	public PaymentRequest() {
		
	}
	
	public PaymentRequest(PaymentRequestBuilder b) {
		super(b);
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
	}
	
	
	
	public static class PaymentRequestBuilder extends CoreEntity.CoreEntityBuilder{
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
		
		public PaymentRequestBuilder() {
			
		}
		
		public PaymentRequestBuilder amount(String amount){
			this.amount = amount;
			return this;
		}
		
		public PaymentRequestBuilder purpose(String purpose){
			this.purpose = purpose;
			return this;
		}
		
		public PaymentRequestBuilder buyerName(String buyer_name){
			this.buyer_name = buyer_name;
			return this;
		}
		
		public PaymentRequestBuilder email(String email){
			this.email = email;
			return this;
		}
		
		public PaymentRequestBuilder phone(String phone){
			this.phone = phone;
			return this;
		}
		
		public PaymentRequestBuilder redirectUrl(String redirect_url){
			this.redirect_url = redirect_url;
			return this;
		}
		
		public PaymentRequestBuilder webhook(String webhook){
			this.webhook = webhook;
			return this;
		}
		
		public PaymentRequestBuilder allowRepeatedPayments(boolean allow_repeated_payments){
			this.allow_repeated_payments = allow_repeated_payments;
			return this;
		}
		
		public PaymentRequestBuilder sendEmail(boolean send_email){
			this.send_email = send_email;
			return this;
		}
		
		public PaymentRequestBuilder sendSMS(boolean send_sms){
			this.send_sms = send_sms;
			return this;
		}
		
		public PaymentRequest build() {
			return new PaymentRequest(this);
		}
	}
	

	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		
	}
	
	
	

	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//putting super object fields into map
		Map<String,Object> superMap = super.map();
		map.putAll(superMap);
		
		map.put("amount",amount);
		map.put("purpose",purpose);
		map.put("buyer_name",buyer_name);
		map.put("email",email);
		map.put("phone",phone);
		map.put("redirect_url",redirect_url);
		map.put("webhook",webhook);
		map.put("allow_repeated_payments",allow_repeated_payments);
		map.put("send_email", send_email);
		map.put("send_sms",send_sms);
		
		return map;
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
