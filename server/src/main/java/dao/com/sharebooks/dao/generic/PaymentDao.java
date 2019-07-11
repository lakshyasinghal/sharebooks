package com.sharebooks.dao.generic;

import java.sql.SQLException;

import com.sharebooks.payment.entities.PaymentRequest;
import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.enums.PaymentStatus;

public interface PaymentDao extends Dao {

	// will create the payment request received from payment gateway servers
	public abstract boolean createPaymentRequest(PaymentRequest paymentRequest) throws SQLException, Exception;

	public abstract String getUserEmailByPaymentId(String payment_id) throws SQLException, Exception;

	// will update the previously received payment request and will create the
	// webhook object
	public abstract boolean updatePaymentRequestAndCreateWebhook(PaymentRequestWebhook paymentRequestWebhook,
			String payment_request_id, PaymentStatus status) throws SQLException, Exception;

}
