package com.sharebooks.util;

import com.sharebooks.config.properties.PaymentProperties;
import com.sharebooks.payment.enums.PaymentType;

public class PaymentRequestUtility {

	public static String getRedirectionUrlPropByPaymentType(PaymentType paymentType) {
		String propName = null;
		if (paymentType == PaymentType.SUBSCRIPTION) {
			propName = PaymentProperties.SUBSCRIPTION_PAYMENT_REDIRECTION_URL;
		}

		return propName;
	}

	public static String getWebhookPropByPaymentType(PaymentType paymentType) {
		String propName = null;
		if (paymentType == PaymentType.SUBSCRIPTION) {
			propName = PaymentProperties.SUBSCRIPTION_PAYMENT_WEBHOOK;
		}

		return propName;
	}
}
