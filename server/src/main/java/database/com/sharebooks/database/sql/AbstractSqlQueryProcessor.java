package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sharebooks.connectionPool.ConnectionPoolManager;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractSqlQueryProcessor {

	public abstract List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws SQLException, Exception;

	// public abstract ResultSet processReadQuery(String dbName, String query)
	// throws SQLException, Exception;

	public abstract List<Map<String, Object>> processReadQuery(String dbName, String query, Map<String, String> columns)
			throws SQLException, Exception;

	public abstract int processDeleteQuery(String dbName, String query) throws SQLException, Exception;

	public abstract int processInsertQuery(String dbName, String query, boolean processInBatch)
			throws SQLException, Exception;

	public abstract int processUpdateQuery(String dbName, String query) throws SQLException, Exception;

	public abstract List<Integer> processTransaction(String dbName, List<String> queries)
			throws SQLException, Exception;

	protected Connection getConnection(String dbName) throws SQLException, Exception {
		Connection conn = ConnectionPoolManager.getSqlConnection(dbName);
		return conn;
	}

	protected void releaseConnection(String dbName, Connection conn) throws SQLException, Exception {
		try {
			ConnectionPoolManager.releaseSqlConnection(dbName, conn);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
