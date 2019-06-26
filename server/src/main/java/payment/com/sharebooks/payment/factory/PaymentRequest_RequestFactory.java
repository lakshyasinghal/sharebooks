package com.sharebooks.payment.factory;

import com.sharebooks.appConfig.AppConfig;
import com.sharebooks.payment.entities.GenericPaymentRequest_Request;
import com.sharebooks.payment.entities.GenericPaymentRequest_Request.GenericPaymentRequest_RequestBuilder;
import com.sharebooks.payment.entities.PaymentRequest_Request;
import com.sharebooks.properties.PaymentProperties;

public class PaymentRequest_RequestFactory {
	private static PaymentRequest_RequestFactory paymentRequest_RequestFactory = null;

	private PaymentRequest_RequestFactory() {
		// nothing goes here
	}

	public static PaymentRequest_RequestFactory getInstance() throws Exception {
		try {
			if (paymentRequest_RequestFactory == null) {
				synchronized (PaymentRequest_RequestFactory.class) {
					if (paymentRequest_RequestFactory == null) {
						paymentRequest_RequestFactory = new PaymentRequest_RequestFactory();
					}
				}
			}

			return paymentRequest_RequestFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public PaymentRequest_Request getPaymentRequest(String amount, String purpose, String buyer_name, String email,
			String phone) {
		String redirect_url = AppConfig.paymentProp(PaymentProperties.REGISTRATION_PAYMENT_REDIRECTION_URL);
		String webhook = AppConfig.paymentProp(PaymentProperties.REGISTRATION_PAYMENT_WEBHOOK);
		boolean allow_repeated_payments = Boolean
				.valueOf(AppConfig.paymentProp(PaymentProperties.ALLOW_REPEATED_PAYMENTS));
		boolean send_email = Boolean.valueOf(AppConfig.paymentProp(PaymentProperties.SEND_EMAIL));
		boolean send_sms = Boolean.valueOf(AppConfig.paymentProp(PaymentProperties.SEND_SMS));
		String x_api_key = AppConfig.paymentProp(PaymentProperties.API_KEY);
		String x_auth_token = AppConfig.paymentProp(PaymentProperties.AUTH_TOKEN);

		GenericPaymentRequest_RequestBuilder builder = new GenericPaymentRequest_Request.GenericPaymentRequest_RequestBuilder();
		builder.amount(amount).purpose(purpose).buyerName(buyer_name).email(email).phone(phone);
		builder.redirectUrl(redirect_url).webhook(webhook).allowRepeatedPayments(allow_repeated_payments)
				.sendEmail(send_email).sendSMS(send_sms).xApiKey(x_api_key).xAuthToken(x_auth_token);
		return builder.build();
	}
}
