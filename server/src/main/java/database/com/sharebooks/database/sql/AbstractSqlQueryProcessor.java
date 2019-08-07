package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;

import com.sharebooks.connectionPool.ConnectionPoolManager;

public abstract class AbstractSqlQueryProcessor implements SqlQueryProcessor {

	// Getting and releasing connections

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
