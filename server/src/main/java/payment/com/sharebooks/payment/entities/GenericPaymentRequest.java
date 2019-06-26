package com.sharebooks.payment.entities;

import org.apache.log4j.Logger;

import com.sharebooks.entities.entity.CoreEntity;

public class GenericPaymentRequest implements PaymentRequest {
	private static Logger LOGGER = Logger.getLogger(GenericPaymentRequest.class);

	private String id;
	private String phone;
	private String email;
	private String buyer_name;
	private String purpose;
	private String status;
	private boolean send_sms;
	private boolean send_email;
	private String sms_status;
	private String email_status;
	private String shorturl;
	private String longurl;
	private String redirect_url;
	private String webhook;
	private String created_at;
	private String modified_at;
	private boolean allow_repeated_payments;

	public GenericPaymentRequest(GenericPaymentRequestBuilder b) {
		id = b.id;
		phone = b.phone;
		email = b.email;
		buyer_name = b.buyer_name;
		purpose = b.purpose;
		status = b.status;
		send_sms = b.send_sms;
		send_email = b.send_email;
		sms_status = b.sms_status;
		email_status = b.email_status;
		shorturl = b.shorturl;
		longurl = b.longurl;
		redirect_url = b.redirect_url;
		webhook = b.webhook;
		created_at = b.created_at;
		modified_at = b.modified_at;
		allow_repeated_payments = b.allow_repeated_payments;
	}

	public static class GenericPaymentRequestBuilder extends CoreEntity.CoreEntityBuilder {
		private String id;
		private String phone;
		private String email;
		private String buyer_name;
		private String purpose;
		private String status;
		private boolean send_sms;
		private boolean send_email;
		private String sms_status;
		private String email_status;
		private String shorturl;
		private String longurl;
		private String redirect_url;
		private String webhook;
		private String created_at;
		private String modified_at;
		private boolean allow_repeated_payments;

		public GenericPaymentRequestBuilder() {

		}

		public GenericPaymentRequestBuilder id(String id) {
			this.id = id;
			return this;
		}

		public GenericPaymentRequestBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public GenericPaymentRequestBuilder email(String email) {
			this.email = email;
			return this;
		}

		public GenericPaymentRequestBuilder buyerName(String buyer_name) {
			this.buyer_name = buyer_name;
			return this;
		}

		public GenericPaymentRequestBuilder purpose(String purpose) {
			this.purpose = purpose;
			return this;
		}

		public GenericPaymentRequestBuilder status(String status) {
			this.status = status;
			return this;
		}

		public GenericPaymentRequestBuilder sendSMS(boolean send_sms) {
			this.send_sms = send_sms;
			return this;
		}

		public GenericPaymentRequestBuilder sendEmail(boolean send_email) {
			this.send_email = send_email;
			return this;
		}

		public GenericPaymentRequestBuilder smsStatus(String sms_status) {
			this.sms_status = sms_status;
			return this;
		}

		public GenericPaymentRequestBuilder emailStatus(String email_status) {
			this.email_status = email_status;
			return this;
		}

		public GenericPaymentRequestBuilder shortUrl(String shorturl) {
			this.shorturl = shorturl;
			return this;
		}

		public GenericPaymentRequestBuilder longUrl(String longurl) {
			this.longurl = longurl;
			return this;
		}

		public GenericPaymentRequestBuilder redirectUrl(String redirect_url) {
			this.redirect_url = redirect_url;
			return this;
		}

		public GenericPaymentRequestBuilder webhook(String webhook) {
			this.webhook = webhook;
			return this;
		}

		public GenericPaymentRequestBuilder createdAt(String created_at) {
			this.created_at = created_at;
			return this;
		}

		public GenericPaymentRequestBuilder modifiedAt(String modified_at) {
			this.modified_at = modified_at;
			return this;
		}

		public GenericPaymentRequestBuilder allowRepeatedPayments(boolean allow_repeated_payments) {
			this.allow_repeated_payments = allow_repeated_payments;
			return this;
		}

		public PaymentRequest build() {
			return new GenericPaymentRequest(this);
		}
	}

	public String id() {
		return id;
	}

	public String phone() {
		return phone;
	}

	public String email() {
		return email;
	}

	public String buyerName() {
		return buyer_name;
	}

	public String purpose() {
		return purpose;
	}

	public String status() {
		return status;
	}

	public boolean sendSMS() {
		return send_sms;
	}

	public boolean sendEmail() {
		return send_email;
	}

	public String smsStatus() {
		return sms_status;
	}

	public String emailStatus() {
		return email_status;
	}

	public String shorturl() {
		return shorturl;
	}

	public String longurl() {
		return longurl;
	}

	public String redirectUrl() {
		return redirect_url;
	}

	public String webhook() {
		return webhook;
	}

	public String createdAt() {
		return created_at;
	}

	public String modifiedAt() {
		return modified_at;
	}

	public boolean allowRepeatedPayments() {
		return allow_repeated_payments;
	}

}
