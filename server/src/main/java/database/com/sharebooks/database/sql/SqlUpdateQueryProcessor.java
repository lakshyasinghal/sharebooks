package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.sharebooks.exception.NonFunctionalMethodException;

public class SqlUpdateQueryProcessor extends AbstractSqlQueryProcessor {
	private static SqlUpdateQueryProcessor queryProcessor = new SqlUpdateQueryProcessor();

	private SqlUpdateQueryProcessor() {

	}

	public static SqlUpdateQueryProcessor getInstance() {
		return queryProcessor;
	}

	@Override
	public int processUpdateQuery(String dbName, String query) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rowsAffected = -1;
		try {
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rowsAffected = executor.executeUpdate(stmt, query);
			return rowsAffected;
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				releaseConnection(dbName, conn);
			}
		}
	}

	@Override
	public List<Integer> processTransaction(String dbName, List<String> queries) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}
}
