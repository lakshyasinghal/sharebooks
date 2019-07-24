package com.sharebooks.factory.dbConnectionFactory;

import java.sql.Connection;

import com.mongodb.MongoClient;

public interface DBConnFactory {

	public Connection getSqlConnection(String host, String port, String dbName, String username, String password)
			throws Exception;

	public MongoClient getMongoConnection(String host, String port, String dbName, String username, String password)
			throws Exception;
}
