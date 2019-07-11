package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

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

	@Override
	public List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws NonFunctionalMethodException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<Map<String, Object>> processReadQuery(String dbName, String query, Map<String, String> columns)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processInsertQuery(String dbName, String query, boolean processInBatch)
			throws NonFunctionalMethodException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processUpdateQuery(String dbName, String query) throws NonFunctionalMethodException, Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<Integer> processTransaction(String dbName, List<String> queries) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

}
