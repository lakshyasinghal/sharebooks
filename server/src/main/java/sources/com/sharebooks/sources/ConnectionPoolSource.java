package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.DatabaseProperties;
import com.sharebooks.connectionPool.DatabaseConnectionPool;
import com.sharebooks.connectionPool.MongoConnectionPool;
import com.sharebooks.connectionPool.MongoConnectionPool.MongoConnectionPoolBuilder;
import com.sharebooks.connectionPool.SqlConnectionPool;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.enums.DatabaseType;

public class ConnectionPoolSource {
	private static Map<String, DatabaseConnectionPool> sqlConnectionPoolMap = new HashMap<String, DatabaseConnectionPool>();
	private static Map<String, DatabaseConnectionPool> mongoConnectionPoolMap = new HashMap<String, DatabaseConnectionPool>();

	public static void init() throws Exception {
		int databaseType = -1;
		String host = null;
		String port = null;
		String username = null;
		String password = null;
		int capacity = -1;

		for (Database database : Database.values()) {
			databaseType = Integer.parseInt(AppConfig.databaseProp(database.desc(), DatabaseProperties.DATABASE_TYPE));
			host = AppConfig.databaseProp(database.desc(), DatabaseProperties.HOST);
			port = AppConfig.databaseProp(database.desc(), DatabaseProperties.PORT);
			username = AppConfig.databaseProp(database.desc(), DatabaseProperties.USERNAME);
			password = AppConfig.databaseProp(database.desc(), DatabaseProperties.PASSWORD);
			capacity = Integer.parseInt(AppConfig.databaseProp(database.desc(), DatabaseProperties.POOL_CAPACITY));

			if (databaseType == DatabaseType.SQL.id()) {
				addToSqlMap(database.desc(), host, port, username, password, capacity);
			} else {
				addToMongoMap(database.desc(), host, port, username, password, capacity);
			}
		}
	}

	public static void addToSqlMap(String dbName, String host, String port, String username, String password,
			int capacity) {
		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.MASTER.desc(), username, password,
				capacity);
		sqlConnectionPoolMap.put(dbName, connectionPool);
	}

	public static void addToMongoMap(String dbName, String host, String port, String username, String password,
			int capacity) throws Exception {
		MongoConnectionPoolBuilder b = new MongoConnectionPool.MongoConnectionPoolBuilder();
		b.name(dbName).host(host).port(Integer.parseInt(port)).username(username).password(password).capacity(capacity);
		mongoConnectionPoolMap.put(dbName, b.build());
	}

	public static Map<String, DatabaseConnectionPool> sqlConnectionPoolMap() {
		return sqlConnectionPoolMap;
	}

	public static Map<String, DatabaseConnectionPool> mongoConnectionPoolMap() {
		return mongoConnectionPoolMap;
	}
}
