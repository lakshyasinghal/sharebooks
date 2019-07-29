package com.sharebooks.database.mongo;

import com.mongodb.client.MongoDatabase;
import com.sharebooks.connectionPool.ConnectionPoolManager;

public class AbstractMongoQueryProcessor {

	protected MongoDatabase getConnection(String dbName) throws Exception {
		MongoDatabase conn = ConnectionPoolManager.getMongoConnection(dbName);
		return conn;
	}

	protected void releaseConnection(String dbName) throws Exception {
		try {
			ConnectionPoolManager.releaseMongoConnection(dbName);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
