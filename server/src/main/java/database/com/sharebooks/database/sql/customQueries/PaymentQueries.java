package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;

import com.sharebooks.database.sql.Table;
import com.sharebooks.payment.enums.PaymentStatus;

public class PaymentQueries {
	private static final Logger LOGGER = Logger.getLogger(PaymentQueries.class.getName());
	private static final PaymentQueries instance = new PaymentQueries();

	private PaymentQueries() {

	}

	public static PaymentQueries instance() {
		return instance;
	}

	public String getUserEmailByPaymentIdQuery(String payment_id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT email FROM ");
		query.append(Table.PAYMENT_REQUEST.desc());
		query.append(" WHERE ");
		query.append("payment_id=");
		query.append("'" + payment_id + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}

	public String updatePaymentStatusInPaymentRequestQuery(String payment_request_id, PaymentStatus status) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(Table.PAYMENT_REQUEST.desc());
		query.append("SET PaymentStatus=");
		query.append(status.id());
		query.append(" WHERE ");
		query.append("payment_request_id=");
		query.append("'" + payment_request_id + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}
}
