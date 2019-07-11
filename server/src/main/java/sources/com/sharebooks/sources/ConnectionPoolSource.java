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
		initMaster();
		initCore();
		initUserAcoounts();
		initUserExperience();
		initPayments();
	}

//	private static void initDriver(){
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} 
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	private static void initMaster() {
		String host = AppConfig.databaseProp(DatabaseProperties.MASTER_HOST);
		String port = AppConfig.databaseProp(DatabaseProperties.MASTER_PORT);
		String username = AppConfig.databaseProp(DatabaseProperties.MASTER_USERNAME);
		String password = AppConfig.databaseProp(DatabaseProperties.MASTER_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.databaseProp(DatabaseProperties.MASTER_POOL_CAPACITY));
		DBConnFactory connFactory = SqlConnFactory.instance();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS_MASTER.desc(),
				username, password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS_MASTER.desc(), connectionPool);
	}

	private static void initCore() {
		String host = AppConfig.databaseProp(DatabaseProperties.CORE_HOST);
		String port = AppConfig.databaseProp(DatabaseProperties.CORE_PORT);
		String username = AppConfig.databaseProp(DatabaseProperties.CORE_USERNAME);
		String password = AppConfig.databaseProp(DatabaseProperties.CORE_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.databaseProp(DatabaseProperties.CORE_POOL_CAPACITY));
		DBConnFactory connFactory = SqlConnFactory.instance();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS_CORE.desc(), username,
				password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS_CORE.desc(), connectionPool);
	}

	private static void initUserAcoounts() {
		String host = AppConfig.databaseProp(DatabaseProperties.USER_ACCOUNTS_HOST);
		String port = AppConfig.databaseProp(DatabaseProperties.USER_ACCOUNTS_PORT);
		String username = AppConfig.databaseProp(DatabaseProperties.USER_ACCOUNTS_USERNAME);
		String password = AppConfig.databaseProp(DatabaseProperties.USER_ACCOUNTS_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.databaseProp(DatabaseProperties.USER_ACCOUNTS_POOL_CAPACITY));
		DBConnFactory connFactory = SqlConnFactory.instance();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS_USER_ACCOUNTS.desc(),
				username, password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS_USER_ACCOUNTS.desc(), connectionPool);
	}

	private static void initUserExperience() {
		String host = AppConfig.databaseProp(DatabaseProperties.USER_EXPERIENCE_HOST);
		String port = AppConfig.databaseProp(DatabaseProperties.USER_EXPERIENCE_PORT);
		String username = AppConfig.databaseProp(DatabaseProperties.USER_EXPERIENCE_USERNAME);
		String password = AppConfig.databaseProp(DatabaseProperties.USER_EXPERIENCE_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.databaseProp(DatabaseProperties.USER_EXPERIENCE_POOL_CAPACITY));
		DBConnFactory connFactory = SqlConnFactory.instance();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS_USER_EXPERIENCE.desc(),
				username, password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS_USER_EXPERIENCE.desc(), connectionPool);
	}

	private static void initPayments() {
		String host = AppConfig.databaseProp(DatabaseProperties.PAYMENTS_HOST);
		String port = AppConfig.databaseProp(DatabaseProperties.PAYMENTS_PORT);
		String username = AppConfig.databaseProp(DatabaseProperties.PAYMENTS_USERNAME);
		String password = AppConfig.databaseProp(DatabaseProperties.PAYMENTS_PASSWORD);
		int capacity = Integer.parseInt(AppConfig.databaseProp(DatabaseProperties.PAYMENTS_POOL_CAPACITY));
		DBConnFactory connFactory = SqlConnFactory.instance();

		SqlConnectionPool connectionPool = new SqlConnectionPool(host, port, Database.SHAREBOOKS_PAYMENTS.desc(),
				username, password, capacity, connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS_PAYMENTS.desc(), connectionPool);
	}

	public static Map<String, ConnectionPool> getConnectionPoolMap() {
		return connectionPoolMap;
	}
}
