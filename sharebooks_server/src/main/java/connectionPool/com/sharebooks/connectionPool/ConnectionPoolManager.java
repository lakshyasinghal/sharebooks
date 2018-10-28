package com.sharebooks.connectionPool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import com.sharebooks.exception.ConnectionPoolException;
import com.sharebooks.sources.ConnectionPoolSource;

public class ConnectionPoolManager {
	private static Map<String , ConnectionPool> connectionPoolMap = new HashMap<String , ConnectionPool>();
	
	static {
		init(ConnectionPoolSource.getConnectionPoolMap());
	}
	
	public static void init(Map<String , ConnectionPool> connectionPoolMap){
		ConnectionPoolManager.connectionPoolMap = connectionPoolMap;
	}
	
	public static Connection getConnection(String dbName) throws ConnectionPoolException,Exception{
		ConnectionPool connectionPool = connectionPoolMap.get(dbName.toLowerCase());
		Connection connection = connectionPool.getSqlConnection(); 
		return connection;
	}
	
	public static void releaseConnection(String dbName , Connection conn) throws ConnectionPoolException,Exception{
		ConnectionPool connectionPool = connectionPoolMap.get(dbName.toLowerCase());
		connectionPool.releaseSqlConnection(conn); 
	}
}
