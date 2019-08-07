package com.sharebooks.database.sql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

public interface SqlQueryProcessor {

	public default List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException("processReadQuery method in SqlQueryProcessor");
	}

	public default List<Map<String, Object>> processReadQuery(String dbName, String query, Map<String, String> columns)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException("processReadQuery method in SqlQueryProcessor");
	}

	public default int processDeleteQuery(String dbName, String query) throws SQLException, Exception {
		throw new NonFunctionalMethodException("processDeleteQuery method in SqlQueryProcessor");
	}

	public default int processInsertQuery(String dbName, String query, boolean processInBatch)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException("processInsertQuery method in SqlQueryProcessor");
	}

	public default int processUpdateQuery(String dbName, String query) throws SQLException, Exception {
		throw new NonFunctionalMethodException("processUpdateQuery method in SqlQueryProcessor");
	}

	// Transactions

	public default List<Integer> processTransaction(String dbName, List<String> queries)
			throws SQLException, Exception {
		throw new NonFunctionalMethodException("processTransaction method in SqlQueryProcessor");
	}
}
