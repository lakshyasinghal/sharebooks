package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;

import com.sharebooks.database.sql.Table;

public class OTPQueries {
	private static final Logger LOGGER = Logger.getLogger(OTPQueries.class);
	private static OTPQueries instance = new OTPQueries();

	private OTPQueries() {

	}

	public static OTPQueries instance() {
		return instance;
	}

	public String queryForMarkAccepted(String uid) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(Table.ONE_TIME_PASSWORDS.desc());
		query.append(" SET ");
		query.append("Accepted=true");
		query.append(" WHERE ");
		query.append("UID='" + uid + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}

	public String queryForMarkRejected(String uid) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(Table.ONE_TIME_PASSWORDS.desc());
		query.append(" SET ");
		query.append("Accepted=false");
		query.append(" WHERE ");
		query.append("UID='" + uid + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}
}
