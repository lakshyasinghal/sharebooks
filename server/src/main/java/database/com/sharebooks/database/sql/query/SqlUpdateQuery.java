package com.sharebooks.database.sql.query;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class SqlUpdateQuery implements SqlQuery {
	private static final Logger LOGGER = Logger.getLogger(SqlUpdateQuery.class.getName());
	private final String table;
	private final Map<String, Object> objMap;
	private static final String ENTITY_IDENTIFIER = "uid";

	private String queryStr = null;

	public SqlUpdateQuery(String table, Map<String, Object> objMap) {
		this.table = table;
		this.objMap = objMap;
	}

	public String toString() {
		if (queryStr == null) {
			build();
		}
		return queryStr;
	}

	/*
	 * function will return update query string Example- UPDATE BOOKS SET
	 * Title='Head First',Available=0 where id=5;
	 */
	public void build() {
		LOGGER.trace("Entering build");
		List<String> fields = fieldsFromMap(objMap);
		List<Object> values = valuesFromMap(objMap);
		Object entityIdentifierVal = null;
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE");
		builder.append(" ");
		builder.append(table);
		builder.append(" ");
		builder.append("SET");
		builder.append(" ");
		int i = 0, len = fields.size();
		boolean started = false; // will be used to assist the comma operation between set values
		String field = null;
		for (; i < len; i++) {
			field = fields.get(i);
			if (ENTITY_IDENTIFIER.equals(field)) {
				entityIdentifierVal = values.get(i);
				continue;
			}
			if (started) { // if set operation started only then put the comma
				builder.append(",");
			}
			builder.append(field);
			builder.append("=");
			builder.append(getValue(values.get(i)));
			started = true;
		}

		builder.append(" ");
		builder.append("where");
		builder.append(" ");
		builder.append(ENTITY_IDENTIFIER);
		builder.append("=");
		builder.append(getValue(entityIdentifierVal));
		builder.append(";");

		queryStr = builder.toString();
		LOGGER.debug("Query String => " + queryStr);
		LOGGER.trace("Leaving build");
	}

	private String getValue(Object obj) {
		String value = null;
		if (obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double
				|| obj instanceof Boolean) {
			value = obj.toString();
		} else if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		}
		return value;
	}
}
