package com.sharebooks.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.PaymentDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.SqlTransactionProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.PaymentQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.payment.entities.PaymentRequest;
import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.enums.PaymentStatus;

public class PaymentSqlDao extends AbstractSqlDao implements PaymentDao {
	private static final Logger LOGGER = Logger.getLogger(PaymentSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_PAYMENTS;
	private final PaymentQueries paymentQueries = PaymentQueries.instance();

	public PaymentSqlDao() {
	}

	@Override
	public boolean createPaymentRequest(PaymentRequest paymentRequest) throws SQLException, Exception {
		LOGGER.trace("Entering createPaymentRequest");
		// get paymentRequest map
		Map<String, Object> paymentRequestMap = paymentRequest.map();
		SqlQuery query = new SqlInsertQuery(Table.PAYMENT_REQUEST.desc(), paymentRequestMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:" + rowsAffected);
		LOGGER.trace("Leaving createPaymentRequest");
		return rowsAffected > 0 ? true : false;
	}

	@Override
	public String getUserEmailByPaymentId(String payment_id) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			LOGGER.trace("Entering getUserEmailByPaymentId");
			String query = paymentQueries.getUserEmailByPaymentIdQuery(payment_id);
			AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();

			// preparing coumn map
			Map<String, String> columnMap = new HashMap<String, String>();
			columnMap.put("email", "string");

			List<Map<String, Object>> resultMapList = queryProcessor.processReadQuery(database.desc(), query,
					columnMap);

			String email = (String) resultMapList.get(0).get("email");
			LOGGER.trace("Leaving getUserEmailByPaymentId");
			return email;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	@Override
	public boolean updatePaymentRequestAndCreateWebhook(PaymentRequestWebhook paymentRequestWebhook,
			String payment_request_id, PaymentStatus status) throws SQLException, Exception {
		LOGGER.trace("Entering updatePaymentRequestAndCreateWebhook");
		boolean updated = true;
		Map<String, Object> paymentRequestWebhookMap = paymentRequestWebhook.map();
		SqlQuery query1 = new SqlInsertQuery(Table.PAYMENT_REQUEST_WEBHOOK.desc(), paymentRequestWebhookMap);
		query1.build();

		String query2 = paymentQueries.updatePaymentStatusInPaymentRequestQuery(payment_request_id, status);

		List<String> queries = new ArrayList<String>();
		queries.add(query1.toString());
		queries.add(query2);
		AbstractSqlQueryProcessor queryProcessor = SqlTransactionProcessor.getInstance();
		List<Integer> rowsAffectedResults = queryProcessor.processTransaction(Database.SHAREBOOKS_PAYMENTS.desc(),
				queries);
		for (Integer rowsAffected : rowsAffectedResults) {
			updated = updated && (rowsAffected > 0);
		}
		LOGGER.trace("Leaving updatePaymentRequestAndCreateWebhook");
		return updated;
	}
}
