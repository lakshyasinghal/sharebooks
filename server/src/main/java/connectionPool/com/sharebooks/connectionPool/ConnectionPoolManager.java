package com.sharebooks.connectionPool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.client.MongoDatabase;
import com.sharebooks.exception.ConnectionPoolException;
import com.sharebooks.sources.ConnectionPoolSource;

public class ConnectionPoolManager {
	private static Map<String, DatabaseConnectionPool> sqlConnectionPoolMap = new HashMap<String, DatabaseConnectionPool>();
	private static Map<String, DatabaseConnectionPool> mongoConnectionPoolMap = new HashMap<String, DatabaseConnectionPool>();

	static {
		putSqlConnectionPoolMap(ConnectionPoolSource.sqlConnectionPoolMap());
		putMongoConnectionPoolMap(ConnectionPoolSource.mongoConnectionPoolMap());
	}

	public static void putSqlConnectionPoolMap(Map<String, DatabaseConnectionPool> sqlConnectionPoolMap) {
		ConnectionPoolManager.sqlConnectionPoolMap = sqlConnectionPoolMap;
	}

	public static void putMongoConnectionPoolMap(Map<String, DatabaseConnectionPool> mongoConnectionPoolMap) {
		ConnectionPoolManager.mongoConnectionPoolMap = mongoConnectionPoolMap;
	}

	public static Connection getSqlConnection(String dbName) throws ConnectionPoolException, Exception {
		DatabaseConnectionPool databaseConnectionPool = sqlConnectionPoolMap.get(dbName);
		Connection connection = (Connection) databaseConnectionPool.getConnection();
		return connection;
	}

	public static void releaseSqlConnection(String dbName, Connection conn) throws ConnectionPoolException, Exception {
		DatabaseConnectionPool databaseConnectionPool = sqlConnectionPoolMap.get(dbName);
		databaseConnectionPool.releaseConnection(conn);
	}

	public static MongoDatabase getMongoConnection(String dbName) throws Exception {
		DatabaseConnectionPool databaseConnectionPool = mongoConnectionPoolMap.get(dbName);
		MongoDatabase connection = (MongoDatabase) databaseConnectionPool.getConnection();
		return connection;
	}

	public static void releaseMongoConnection(String dbName) throws ConnectionPoolException, Exception {
		DatabaseConnectionPool databaseConnectionPool = mongoConnectionPoolMap.get(dbName);
		databaseConnectionPool.releaseConnection(null);
	}
}
