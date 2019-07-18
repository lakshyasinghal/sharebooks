package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;

import com.sharebooks.database.sql.Table;

public class PasswordResetLinkQueries {
	private static final Logger LOGGER = Logger.getLogger(PasswordResetLinkQueries.class);
	private static PasswordResetLinkQueries instance = new PasswordResetLinkQueries();

	private PasswordResetLinkQueries() {

	}

	public static PasswordResetLinkQueries instance() {
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
