package com.sharebooks.connectionPool;

public interface DatabaseConnectionPool {

	public void init(int capacity) throws Exception;

	public Object getConnection() throws Exception;

	public void releaseConnection(Object obj) throws Exception;

	public int capacity();
}
