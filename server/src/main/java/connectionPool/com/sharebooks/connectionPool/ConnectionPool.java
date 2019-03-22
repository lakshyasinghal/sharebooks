package com.sharebooks.connectionPool;

import java.sql.Connection;

public interface ConnectionPool {

	public void init(int capacity) throws Exception;
	
	public Connection getSqlConnection() throws Exception;
	
	public void releaseSqlConnection(Connection conn) throws Exception;
	
	public int capacity();
}
