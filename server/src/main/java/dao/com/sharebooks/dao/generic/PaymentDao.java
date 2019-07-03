package com.sharebooks.dao.generic;

import java.sql.SQLException;

import com.sharebooks.payment.entities.PaymentRequest;
import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.enums.PaymentStatus;

public interface PaymentDao extends Dao {

	public abstract boolean createPaymentRequest(PaymentRequest paymentRequest) throws SQLException, Exception;

	public abstract String getUserEmailByPaymentId(String payment_id) throws SQLException, Exception;

	public abstract boolean updatePaymentRequestAndCreateWebhook(PaymentRequestWebhook paymentRequestWebhook,
			String payment_request_id, PaymentStatus status) throws SQLException, Exception;

}
