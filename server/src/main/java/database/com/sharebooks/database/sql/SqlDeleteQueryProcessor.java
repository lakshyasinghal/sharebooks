package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class SqlDeleteQueryProcessor extends AbstractSqlQueryProcessor {
	private static final SqlDeleteQueryProcessor queryProcessor = new SqlDeleteQueryProcessor();
	private static final Logger LOGGER = Logger.getLogger(SqlDeleteQueryProcessor.class.getName());

	private SqlDeleteQueryProcessor() {

	}

	public static SqlDeleteQueryProcessor getInstance() {
		return queryProcessor;
	}

	public int processDeleteQuery(String dbName, String query) throws Exception {
		// LOGGER.entering("SqlDeleteQueryProcessor", "processDeleteQuery");
		// LOGGER.finer("dbName:"+dbName);
		Connection conn = null;
		Statement stmt = null;
		int rowsAffected = -1;
		try {
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rowsAffected = executor.executeUpdate(stmt, query);
			// LOGGER.exiting("SqlDeleteQueryProcessor", "processDeleteQuery");
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

}
