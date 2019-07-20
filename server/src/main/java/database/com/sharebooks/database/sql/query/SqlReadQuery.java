package com.sharebooks.database.sql.query;

import java.util.Map;

import org.apache.log4j.Logger;

public class SqlReadQuery implements SqlQuery {
	private static final Logger LOGGER = Logger.getLogger(SqlReadQuery.class);
	private final String tableName;
	private final Map<String, Object> whereParamsMap;

	private String queryStr = null;

	public SqlReadQuery(String tableName, Map<String, Object> whereParamsMap) {
		this.tableName = tableName;
		this.whereParamsMap = whereParamsMap;
	}

	public String toString() {
		if (queryStr == null) {
			build();
		}
		return queryStr;
	}

	public void build() {
		StringBuilder query = new StringBuilder();
		int i = 0;
		query.append("SELECT * FROM ");
		query.append(tableName);
		if (whereParamsMap != null && whereParamsMap.size() > 0) {
			query.append(" where ");
			for (String key : whereParamsMap.keySet()) {
				if (i > 0) {
					query.append(" and ");
				}
				query.append(getWhereCondition(key, whereParamsMap.get(key)));
				i++;
			}
		}
		query.append(";");
		queryStr = query.toString();
		LOGGER.debug("Query String => " + queryStr);
	}

	public String getWhereCondition(String key, Object value) {
		StringBuilder condition = new StringBuilder();
		condition.append(key);
		condition.append("=");
		if (value instanceof String) {
			condition.append("'");
			condition.append(value);
			condition.append("'");
		} else {
			condition.append(value);
		}
		return condition.toString();
	}
}
