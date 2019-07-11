package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

public class SqlTransactionProcessor extends AbstractSqlQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(SqlTransactionProcessor.class.getName());
	private static SqlTransactionProcessor queryProcessor = new SqlTransactionProcessor();

	private SqlTransactionProcessor() {

	}

	public static SqlTransactionProcessor getInstance() {
		return queryProcessor;
	}

	@Override
	public List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<Map<String, Object>> processReadQuery(String dbName, String query, Map<String, String> columns)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processDeleteQuery(String dbName, String query) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processInsertQuery(String dbName, String query, boolean processInBatch) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processUpdateQuery(String dbName, String query) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<Integer> processTransaction(String dbName, List<String> queries) throws SQLException, Exception {
		LOGGER.trace("Entered processTransaction");
		Connection conn = null;
		Statement stmt = null;
		List<Integer> rowsAffectedResults = new ArrayList<Integer>();
		try {
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			conn.setAutoCommit(false);
			int rowsAffected = -1;
			for (String query : queries) {
				LOGGER.debug("Query => " + query);
				try {
					LOGGER.debug("Getting statement");
					stmt = conn.createStatement();
					rowsAffected = executor.executeUpdate(stmt, query);
					rowsAffectedResults.add(rowsAffected);
				} finally {
					LOGGER.debug("Closing statement");
					if (stmt != null) {
						stmt.close();
						LOGGER.debug("Statement closed.");
					} else {
						LOGGER.debug("null statement");
					}
				}
			}
			conn.commit();
			LOGGER.trace("Leaving processTransaction");
			return rowsAffectedResults;
		} catch (SQLException ex) {
			if (conn != null) {
				conn.rollback();
			}
			throw ex;
		} catch (Exception ex) {
			if (conn != null) {
				conn.rollback();
			}
			throw ex;
		} finally {
			if (conn != null) {
				releaseConnection(dbName, conn);
			}
		}

	}

}
