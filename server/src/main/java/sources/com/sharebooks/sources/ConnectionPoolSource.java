package com.sharebooks.sources;

import java.util.*;
import com.sharebooks.connectionPool.ConnectionPool;
import com.sharebooks.connectionPool.SqlConnectionPool;
import com.sharebooks.database.sql.Database;
import com.sharebooks.factory.dbConnectionFactory.DBConnFactory;
import com.sharebooks.factory.dbConnectionFactory.SqlConnFactory;

public class ConnectionPoolSource {
	private static Map<String , ConnectionPool> connectionPoolMap = new HashMap<String , ConnectionPool>(); 
	
	
	
	public static void init(){
		//initDriver();
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
	
	private static void initSharebooksConnectionPool(){
		String host = PropertySource.getDBProperty("SHAREBOOKS_DB_HOST");
		String port = PropertySource.getDBProperty("SHAREBOOKS_DB_PORT");
		String username = PropertySource.getDBProperty("SHAREBOOKS_DB_USERNAME");
		String password = PropertySource.getDBProperty("SHAREBOOKS_DB_PASSWORD");
		int capacity = Integer.parseInt(PropertySource.getDBProperty("SHAREBOOKS_CONNECTION_POOL_CAPACITY"));
		DBConnFactory connFactory = new SqlConnFactory();
		
		SqlConnectionPool connectionPool = new SqlConnectionPool(host , port , Database.SHAREBOOKS.desc() , username , password , capacity , connFactory);
		connectionPoolMap.put(Database.SHAREBOOKS.desc(), connectionPool);
	}
	
	
	public static Map<String , ConnectionPool> getConnectionPoolMap(){
		return connectionPoolMap;
	}
}
