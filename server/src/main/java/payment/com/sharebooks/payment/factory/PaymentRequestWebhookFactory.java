package com.sharebooks.payment.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.entities.PaymentRequestWebhook.PaymentRequestWebhookBuilder;
import com.sharebooks.payment.enums.PaymentStatus;
import com.sharebooks.util.HttpUtility;

public class PaymentRequestWebhookFactory {
	private static Logger LOGGER = Logger.getLogger(PaymentRequestWebhookFactory.class);
	private static PaymentRequestWebhookFactory instance = null;

	private PaymentRequestWebhookFactory() {

	}

	public static PaymentRequestWebhookFactory instance() {
		if (instance == null) {
			synchronized (PaymentRequestWebhookFactory.class) {
				if (instance == null) {
					instance = new PaymentRequestWebhookFactory();
				}
			}
		}
		return instance;
	}

	public PaymentRequestWebhook createFromHttpRequest(HttpServletRequest req) throws Exception {
		double amount = HttpUtility.doubleVal(req, "amount");
		String buyer = req.getParameter("buyer");
		String buyer_name = req.getParameter("buyer_name");
		String buyer_phone = req.getParameter("buyer_phone");
		String currency = req.getParameter("currency");
		double fees = HttpUtility.doubleVal(req, "fees");
		String longurl = req.getParameter("longurl");
		String mac = req.getParameter("mac");
		String payment_id = req.getParameter("payment_id");
		String payment_request_id = req.getParameter("payment_request_id");
		String purpose = req.getParameter("purpose");
		String shorturl = req.getParameter("shorturl");
		PaymentStatus status = PaymentStatus.get(req.getParameter("status"));

		PaymentRequestWebhookBuilder builder = new PaymentRequestWebhook.PaymentRequestWebhookBuilder();
		builder.amount(amount).buyer(buyer).buyerName(buyer_name).buyerPhone(buyer_phone).currency(currency).fees(fees)
				.longurl(longurl).mac(mac).paymentId(payment_id).paymentRequestId(payment_request_id).purpose(purpose)
				.shorturl(shorturl).status(status);
		return builder.build();
	}
}
