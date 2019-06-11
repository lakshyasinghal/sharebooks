package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.appConfig.AppConfig;
import com.sharebooks.connectionPool.ConnectionPool;
import com.sharebooks.connectionPool.SqlConnectionPool;
import com.sharebooks.database.sql.Database;
import com.sharebooks.factory.dbConnectionFactory.DBConnFactory;
import com.sharebooks.factory.dbConnectionFactory.SqlConnFactory;
import com.sharebooks.properties.DatabaseProperties;

public class ConnectionPoolSource {
	private static Map<String, ConnectionPool> connectionPoolMap = new HashMap<String, ConnectionPool>();

	public static void init() {
		// initDriver();
		initSharebooksConnectionPool();
	}

//	private static void initDriver(){
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} 
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	private static void initSharebooksConnectionPool() {
		String host = AppConfig.getDatabaseProperty(DatabaseProperties.SHAREBOOKS_DB_HOST);
		String port = AppConfig.getDatabaseProperty(DatabaseProperties.SHAREBOOKS_DB_PORT);
		String username = AppConfig.getDatabaseProperty(DatabaseProperties.SHAREBOOKS_DB_USERNAME);
		String password = AppConfig.getDatabaseProperty(DatabaseProperties.SHAREBOOKS_DB_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.getDatabaseProperty(DatabaseProperties.SHAREBOOKS_CONNECTION_POOL_CAPACITY));
		DBConnFactory connFactory = new SqlConnFactory();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS.desc(), username,
				password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS.desc(), connectionPool);
	}

	public static Map<String, ConnectionPool> getConnectionPoolMap() {
		return connectionPoolMap;
	}
}
